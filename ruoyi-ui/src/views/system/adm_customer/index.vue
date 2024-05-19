<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="顾客性别" prop="cusex">
        <el-select v-model="queryParams.cusex" placeholder="请选择顾客性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="顾客姓名" prop="unikename">
        <el-input
          v-model="queryParams.unikename"
          placeholder="请输入顾客姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登录账号" prop="uname">
        <el-input
          v-model="queryParams.uname"
          placeholder="请输入登录账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="顾客手机" prop="unum">
        <el-input
          v-model="queryParams.unum"
          placeholder="请输入顾客手机"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="顾客类型" prop="cutp">
        <el-select v-model="queryParams.cutp" placeholder="请选择顾客类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_customer_type"
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
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:adm_customer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:adm_customer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="adm_customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="顾客编号" align="center" prop="cuid" />
      <el-table-column label="顾客性别" align="center" prop="cusex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.cusex"/>
        </template>
      </el-table-column>
      <el-table-column label="顾客电话" align="center" prop="unum" />
      <el-table-column label="顾客姓名" align="center" prop="unikename" />
      <el-table-column label="登录账号" align="center" prop="uname" />
      <el-table-column label="顾客类型" align="center" prop="cutp">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_customer_type" :value="scope.row.cutp"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:adm_customer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:adm_customer:remove']"
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

    <!-- 添加或修改顾客管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="顾客性别" prop="cusex">
          <el-select v-model="form.cusex" placeholder="请选择顾客性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="顾客类型" prop="cutp">
          <el-select v-model="form.cutp" placeholder="请选择顾客类型">
            <el-option
              v-for="dict in dict.type.sys_customer_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listAdm_customer, getAdm_customer, delAdm_customer, addAdm_customer, updateAdm_customer } from "@/api/system/adm_customer";

export default {
  name: "Adm_customer",
  dicts: ['sys_user_sex', 'sys_customer_type'],
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
      // 顾客管理表格数据
      adm_customerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cusex: null,
        cunum: null,
        cuname: null,
        uname: null,
        unikename: null,
        unum: null,
        uid: null,
        cutp: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询顾客管理列表 */
    getList() {
      this.loading = true;
      listAdm_customer(this.queryParams).then(response => {
        this.adm_customerList = response.rows;
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
        cuid: null,
        cusex: null,
        cunum: null,
        cuname: null,
        uid: null,
        cutp: null
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
      this.ids = selection.map(item => item.cuid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加顾客管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cuid = row.cuid || this.ids
      getAdm_customer(cuid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改顾客管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cuid != null) {
            updateAdm_customer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdm_customer(this.form).then(response => {
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
      const cuids = row.cuid || this.ids;
      this.$modal.confirm('是否确认删除顾客管理编号为"' + cuids + '"的数据项？').then(function() {
        return delAdm_customer(cuids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/adm_customer/export', {
        ...this.queryParams
      }, `adm_customer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
