<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务种类" prop="stid">
        <el-input
          v-model="queryParams.stid"
          placeholder="请输入服务种类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店员" prop="clid">
        <el-input
          v-model="queryParams.clid"
          placeholder="请输入店员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物店" prop="mid">
        <el-input
          v-model="queryParams.mid"
          placeholder="请输入宠物店"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物编号" prop="pid">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入宠物编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="serstime">
        <el-date-picker clearable
          v-model="queryParams.serstime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择服务开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="seretime">
        <el-date-picker clearable
          v-model="queryParams.seretime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择服务结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="服务评分" prop="serscore">
        <el-select v-model="queryParams.serscore" placeholder="请选择服务评分" clearable>
          <el-option
            v-for="dict in dict.type.sys_service_score"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="服务评价" prop="serassess">
        <el-input
          v-model="queryParams.serassess"
          placeholder="请输入服务评价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务状态" prop="serstate">
        <el-select v-model="queryParams.serstate" placeholder="请选择服务状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_service_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="服务花费" prop="sermoney">
        <el-input
          v-model="queryParams.sermoney"
          placeholder="请输入服务花费"
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
          v-hasPermi="['system:adm_service:add']"
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
          v-hasPermi="['system:adm_service:edit']"
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
          v-hasPermi="['system:adm_service:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:adm_service:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="adm_serviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务记录编号" align="center" prop="serid" />
      <el-table-column label="服务种类" align="center" prop="stid" />
      <el-table-column label="店员" align="center" prop="clid" />
      <el-table-column label="宠物店" align="center" prop="mid" />
      <el-table-column label="宠物编号" align="center" prop="pid" />
      <el-table-column label="开始时间" align="center" prop="serstime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.serstime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="seretime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.seretime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务评分" align="center" prop="serscore">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_service_score" :value="scope.row.serscore"/>
        </template>
      </el-table-column>
      <el-table-column label="服务评价" align="center" prop="serassess" />
      <el-table-column label="服务状态" align="center" prop="serstate">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_service_state" :value="scope.row.serstate"/>
        </template>
      </el-table-column>
      <el-table-column label="服务花费" align="center" prop="sermoney" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:adm_service:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:adm_service:remove']"
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

    <!-- 添加或修改服务记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务种类" prop="stid">
          <el-input v-model="form.stid" placeholder="请输入服务种类" />
        </el-form-item>
        <el-form-item label="店员" prop="clid">
          <el-input v-model="form.clid" placeholder="请输入店员" />
        </el-form-item>
        <el-form-item label="宠物店" prop="mid">
          <el-input v-model="form.mid" placeholder="请输入宠物店" />
        </el-form-item>
        <el-form-item label="宠物编号" prop="pid">
          <el-input v-model="form.pid" placeholder="请输入宠物编号" />
        </el-form-item>
        <el-form-item label="服务开始时间" prop="serstime">
          <el-date-picker clearable
            v-model="form.serstime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择服务开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务结束时间" prop="seretime">
          <el-date-picker clearable
            v-model="form.seretime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择服务结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务评分" prop="serscore">
          <el-select v-model="form.serscore" placeholder="请选择服务评分">
            <el-option
              v-for="dict in dict.type.sys_service_score"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务评价" prop="serassess">
          <el-input v-model="form.serassess" placeholder="请输入服务评价" />
        </el-form-item>
        <el-form-item label="服务状态" prop="serstate">
          <el-select v-model="form.serstate" placeholder="请选择服务状态">
            <el-option
              v-for="dict in dict.type.sys_service_state"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务花费" prop="sermoney">
          <el-input v-model="form.sermoney" placeholder="请输入服务花费" />
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
import { listAdm_service, getAdm_service, delAdm_service, addAdm_service, updateAdm_service } from "@/api/system/adm_service";

export default {
  name: "Adm_service",
  dicts: ['sys_service_state', 'sys_service_score'],
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
      // 服务记录表格数据
      adm_serviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stid: null,
        clid: null,
        mid: null,
        pid: null,
        serstime: null,
        seretime: null,
        serscore: null,
        serassess: null,
        serstate: null,
        sermoney: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stid: [
          { required: true, message: "服务种类不能为空", trigger: "blur" }
        ],
        clid: [
          { required: true, message: "店员不能为空", trigger: "blur" }
        ],
        mid: [
          { required: true, message: "宠物店不能为空", trigger: "blur" }
        ],
        pid: [
          { required: true, message: "宠物编号不能为空", trigger: "blur" }
        ],
        serstime: [
          { required: true, message: "服务开始时间不能为空", trigger: "blur" }
        ],
        seretime: [
          { required: true, message: "服务结束时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询服务记录列表 */
    getList() {
      this.loading = true;
      listAdm_service(this.queryParams).then(response => {
        this.adm_serviceList = response.rows;
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
        serid: null,
        stid: null,
        clid: null,
        mid: null,
        pid: null,
        serstime: null,
        seretime: null,
        serscore: null,
        serassess: null,
        serstate: null,
        sermoney: null
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
      this.ids = selection.map(item => item.serid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加服务记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serid = row.serid || this.ids
      getAdm_service(serid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改服务记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serid != null) {
            updateAdm_service(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdm_service(this.form).then(response => {
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
      const serids = row.serid || this.ids;
      this.$modal.confirm('是否确认删除服务记录编号为"' + serids + '"的数据项？').then(function() {
        return delAdm_service(serids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/adm_service/export', {
        ...this.queryParams
      }, `adm_service_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
