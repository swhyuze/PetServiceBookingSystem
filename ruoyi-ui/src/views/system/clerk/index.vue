<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="店员姓名" prop="clname">
        <el-input
          v-model="queryParams.clname"
          placeholder="请输入店员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店员性别" prop="clsex">
        <el-select v-model="queryParams.clsex" placeholder="请选择店员性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="店员电话" prop="clnum">
        <el-input
          v-model="queryParams.clnum"
          placeholder="请输入店员电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上司编号" prop="mid">
        <el-input
          v-model="queryParams.mid"
          placeholder="请输入上司编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登录账号" prop="uid">
        <el-input
          v-model="queryParams.uid"
          placeholder="请输入登录账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:clerk:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:clerk:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:clerk:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:clerk:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="clerkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="店员编号" align="center" prop="clid" />
      <el-table-column label="店员姓名" align="center" prop="clname" />
      <el-table-column label="店员性别" align="center" prop="clsex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.clsex"/>
        </template>
      </el-table-column>
      <el-table-column label="店员电话" align="center" prop="clnum" />
      <el-table-column label="上司编号" align="center" prop="mid" />
      <el-table-column label="登录账号" align="center" prop="uid" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:clerk:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:clerk:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改店员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店员姓名" prop="clname">
          <el-input v-model="form.clname" placeholder="请输入店员姓名" />
        </el-form-item>
        <el-form-item label="店员性别" prop="clsex">
          <el-select v-model="form.clsex" placeholder="请选择店员性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="店员电话" prop="clnum">
          <el-input v-model="form.clnum" placeholder="请输入店员电话" />
        </el-form-item>
        <el-form-item label="上司编号" prop="mid">
          <el-input v-model="form.mid" placeholder="请输入上司编号" />
        </el-form-item>
        <el-form-item label="登录账号" prop="uid">
          <el-input v-model="form.uid" placeholder="请输入登录账号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClerk, getClerk, delClerk, addClerk, updateClerk } from "@/api/system/clerk";

export default {
  name: "Clerk",
  dicts: ['sys_user_sex'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 店员表格数据
      clerkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clname: null,
        clsex: null,
        clnum: null,
        mid: null,
        uid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clid: [
          { required: true, message: "店员编号不能为空", trigger: "blur" }
        ],
        clname: [
          { required: true, message: "店员姓名不能为空", trigger: "blur" }
        ],
        mid: [
          { required: true, message: "上司编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询店员列表 */
    getList() {
      this.loading = true;
      listClerk(this.queryParams).then(response => {
        this.clerkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        clid: null,
        clname: null,
        clsex: null,
        clnum: null,
        mid: null,
        uid: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.clid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加店员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const clid = row.clid || this.ids
      getClerk(clid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改店员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.clid != null) {
            updateClerk(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClerk(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const clids = row.clid || this.ids;
      this.$modal.confirm('是否确认删除店员编号为"' + clids + '"的数据项？').then(function() {
        return delClerk(clids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/clerk/export', {
        ...this.queryParams
      }, `clerk_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
