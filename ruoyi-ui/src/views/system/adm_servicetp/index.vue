<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务名称" prop="stname">
        <el-input
          v-model="queryParams.stname"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务价格" prop="stmoney">
        <el-input
          v-model="queryParams.stmoney"
          placeholder="请输入服务价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收费方式" prop="stmtp">
        <el-select v-model="queryParams.stmtp" placeholder="请选择收费方式" clearable>
          <el-option
            v-for="dict in dict.type.sys_shoufei"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="服务描述" prop="stps">
        <el-input
          v-model="queryParams.stps"
          placeholder="请输入服务描述"
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
          v-hasPermi="['system:adm_servicetp:add']"
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
          v-hasPermi="['system:adm_servicetp:edit']"
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
          v-hasPermi="['system:adm_servicetp:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:adm_servicetp:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="adm_servicetpList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务种类编号" align="center" prop="stid" />
      <el-table-column label="服务名称" align="center" prop="stname" />
      <el-table-column label="服务价格" align="center" prop="stmoney" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:adm_servicetp:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:adm_servicetp:remove']"
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

    <!-- 添加或修改服务种类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务名称" prop="stname">
          <el-input v-model="form.stname" placeholder="请输入服务名称" />
        </el-form-item>
        <el-form-item label="服务价格" prop="stmoney">
          <el-input v-model="form.stmoney" placeholder="请输入服务价格" />
        </el-form-item>
        <el-form-item label="收费方式" prop="stmtp">
          <el-select v-model="form.stmtp" placeholder="请选择收费方式">
            <el-option
              v-for="dict in dict.type.sys_shoufei"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务描述" prop="stps">
          <el-input v-model="form.stps" placeholder="请输入服务描述" />
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
import { listAdm_servicetp, getAdm_servicetp, delAdm_servicetp, addAdm_servicetp, updateAdm_servicetp } from "@/api/system/adm_servicetp";

export default {
  name: "Adm_servicetp",
  dicts: ['sys_shoufei'],
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
      // 服务种类表格数据
      adm_servicetpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stname: null,
        stmoney: null,
        stmtp: null,
        stps: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stname: [
          { required: true, message: "服务名称不能为空", trigger: "blur" }
        ],
        stmoney: [
          { required: true, message: "服务价格不能为空", trigger: "blur" }
        ],
        stmtp: [
          { required: true, message: "收费方式不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询服务种类列表 */
    getList() {
      this.loading = true;
      listAdm_servicetp(this.queryParams).then(response => {
        this.adm_servicetpList = response.rows;
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
        stid: null,
        stname: null,
        stmoney: null,
        stmtp: null,
        stps: null
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
      this.ids = selection.map(item => item.stid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加服务种类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stid = row.stid || this.ids
      getAdm_servicetp(stid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改服务种类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stid != null) {
            updateAdm_servicetp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdm_servicetp(this.form).then(response => {
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
      const stids = row.stid || this.ids;
      this.$modal.confirm('是否确认删除服务种类编号为"' + stids + '"的数据项？').then(function() {
        return delAdm_servicetp(stids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/adm_servicetp/export', {
        ...this.queryParams
      }, `adm_servicetp_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
