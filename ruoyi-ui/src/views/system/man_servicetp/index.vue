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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="man_servicetpList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务名称" align="center" prop="stname" />
      <el-table-column label="服务价格" align="center" prop="stmoney" />
      <el-table-column label="收费方式" align="center" prop="stmtp">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_shoufei" :value="scope.row.stmtp"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:man_servicetp:edit']"
          >查看</el-button>
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMan_servicetp, getMan_servicetp, delMan_servicetp, addMan_servicetp, updateMan_servicetp } from "@/api/system/man_servicetp";

export default {
  name: "Man_servicetp",
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
      man_servicetpList: [],
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
      listMan_servicetp(this.queryParams).then(response => {
        this.man_servicetpList = response.rows;
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
      getMan_servicetp(stid).then(response => {
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
            updateMan_servicetp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMan_servicetp(this.form).then(response => {
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
        return delMan_servicetp(stids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/man_servicetp/export', {
        ...this.queryParams
      }, `man_servicetp_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
