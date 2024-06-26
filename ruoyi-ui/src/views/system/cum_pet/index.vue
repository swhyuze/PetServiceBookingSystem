<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="宠物姓名" prop="pname">
        <el-input
          v-model="queryParams.pname"
          placeholder="请输入宠物姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物性别" prop="psex">
        <el-select v-model="queryParams.psex" placeholder="请选择宠物性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_pet_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:cum_pet:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cum_petList" @selection-change="handleSelectionChange">
      <el-table-column label="宠物姓名" align="center" prop="pname" />
      <el-table-column label="注意事项" align="center" prop="pps" />
      <el-table-column label="宠物性别" align="center" prop="psex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_pet_sex" :value="scope.row.psex"/>
        </template>
      </el-table-column>
      <el-table-column label="品种名称" align="center" prop="ptname" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cum_pet:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cum_pet:remove']"
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

    <!-- 添加或修改宠物管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="宠物姓名" prop="pname">
          <el-input v-model="form.pname" placeholder="请输入宠物姓名" />
        </el-form-item>
        <el-form-item label="宠物性别" prop="psex">
          <el-select v-model="form.psex" placeholder="请选择宠物性别">
            <el-option
              v-for="dict in dict.type.sys_pet_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="宠物品种" prop="ptid">
          <el-select v-model="form.ptid" placeholder="请选择宠物品种">
            <el-option
              v-for="dict in options"
              :key="dict.ptid"
              :label="dict.ptname"
              :value="parseInt(dict.ptid)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="注意事项" prop="pps">
          <el-input v-model="form.pps" placeholder="请输入注意事项" />
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
import { listCum_pet, getCum_pet, delCum_pet, addCum_pet, updateCum_pet ,selectAdmPsbsPettpAll} from "@/api/system/cum_pet";

export default {
  name: "Cum_pet",
  dicts: ['sys_pet_sex'],
  data() {
    return {
      options:[],
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
      // 宠物管理表格数据
      cum_petList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pname: null,
        pps: null,
        psex: null,
        ptid: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pname: [
          { required: true, message: "宠物姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.selectAdmPsbsPettpAll();
  },
  methods: {
    /** 下拉菜单 */
    selectAdmPsbsPettpAll() {
      selectAdmPsbsPettpAll().then(response => {
        console.log(response);
        this.options=response.rows;
      });
    },
    /** 查询宠物管理列表 */
    getList() {
      this.loading = true;
      listCum_pet(this.queryParams).then(response => {
        this.cum_petList = response.rows;
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
        pid: null,
        pname: null,
        pps: null,
        psex: null,
        ptid: null,
        ptpic: null
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
      this.ids = selection.map(item => item.pid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加宠物";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.selectAdmPsbsPettpAll();
      const pid = row.pid || this.ids
      getCum_pet(pid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pid != null) {
            updateCum_pet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCum_pet(this.form).then(response => {
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
      const pids = row.pid || this.ids;
      this.$modal.confirm('是否确认删除宠物管理编号为"' + pids + '"的数据项？').then(function() {
        return delCum_pet(pids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/cum_pet/export', {
        ...this.queryParams
      }, `cum_pet_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
