<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="店长性别" prop="msex">
        <el-select v-model="queryParams.msex" placeholder="请选择店长性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="店长电话" prop="mnum">
        <el-input
          v-model="queryParams.mnum"
          placeholder="请输入店长电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店面地址" prop="maddress">
        <el-input
          v-model="queryParams.maddress"
          placeholder="请输入店面地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店长姓名" prop="mname">
        <el-input
          v-model="queryParams.mname"
          placeholder="请输入店长姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店面名称" prop="msname">
        <el-input
          v-model="queryParams.msname"
          placeholder="请输入店面名称"
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
          v-hasPermi="['system:manager:add']"
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
          v-hasPermi="['system:manager:edit']"
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
          v-hasPermi="['system:manager:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:manager:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="managerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="店长编号" align="center" prop="mid" />
      <el-table-column label="店长性别" align="center" prop="msex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.msex"/>
        </template>
      </el-table-column>
      <el-table-column label="店长电话" align="center" prop="mnum" />
      <el-table-column label="店面地址" align="center" prop="maddress" />
      <el-table-column label="店长姓名" align="center" prop="mname" />
      <el-table-column label="店面名称" align="center" prop="msname" />
      <el-table-column label="登录账号" align="center" prop="uid" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:manager:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:manager:remove']"
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

    <!-- 添加或修改宠物店管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店长性别" prop="msex">
          <el-select v-model="form.msex" placeholder="请选择店长性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="店长电话" prop="mnum">
          <el-input v-model="form.mnum" placeholder="请输入店长电话" />
        </el-form-item>
        <el-form-item label="店面地址" prop="maddress">
          <el-input v-model="form.maddress" placeholder="请输入店面地址" />
        </el-form-item>
        <el-form-item label="店长姓名" prop="mname">
          <el-input v-model="form.mname" placeholder="请输入店长姓名" />
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
import { listManager, getManager, delManager, addManager, updateManager } from "@/api/system/manager";

export default {
  name: "Manager",
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
      // 宠物店管理表格数据
      managerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        msex: null,
        mnum: null,
        maddress: null,
        mname: null,
        msname: null,
        uid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mid: [
          { required: true, message: "店长编号不能为空", trigger: "blur" }
        ],
        mname: [
          { required: true, message: "店长姓名不能为空", trigger: "blur" }
        ],
        msname: [
          { required: true, message: "店面名称不能为空", trigger: "blur" }
        ],
        uid: [
          { required: true, message: "登录账号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询宠物店管理列表 */
    getList() {
      this.loading = true;
      listManager(this.queryParams).then(response => {
        this.managerList = response.rows;
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
        mid: null,
        msex: null,
        mnum: null,
        maddress: null,
        mname: null,
        msname: null,
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
      this.ids = selection.map(item => item.mid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加宠物店管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mid = row.mid || this.ids
      getManager(mid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物店管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mid != null) {
            updateManager(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManager(this.form).then(response => {
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
      const mids = row.mid || this.ids;
      this.$modal.confirm('是否确认删除宠物店管理编号为"' + mids + '"的数据项？').then(function() {
        return delManager(mids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/manager/export', {
        ...this.queryParams
      }, `manager_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
