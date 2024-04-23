<template>
  <div class="byted-weektime">
    <div class="calendar" style="width:calc(100% - -1px);">
      <table class="calendar-table" style="width:100%">
        <thead class="calendar-head">
          <tr>
            <td colspan="6" v-for="index in tableHeader" :key="index">{{ index }}</td>
          </tr>
        </thead>
        <tbody id="tableBody">
          <div
            id="kuang"
            :style="{
              width: kuangObj.width + 'px',
              height: kuangObj.height + 'px',
              top: kuangObj.top + 'px',
              left: kuangObj.left + 'px',
              bottom: kuangObj.bottom + 'px',
              right: kuangObj.right + 'px'
            }"
          ></div>
          <tr>
            <td
              @mousedown.prevent="handleMouseDown(i, 0)"
              @mouseup.prevent="handleMouseUp(i, 0)"
              @mouseover.prevent="handleMouseOver(i, 0)"
              class="calendar-atom-time"
              :class="item.hoverClass || item.class"
              v-for="(item, i) in rowUnit"
              :key="i"
              :title="toStr(i / 6)"
            ></td>
          </tr>
 
          <tr v-if="edit">
            <td colspan="145" class="td-table-tip">
              <div class="clearfix"><span class="pull-left tip-text">请用鼠标点选时间段</span> <a @click="clear" class="pull-right"> 清空</a></div>
            </td>
          </tr>
          <tr>
            <td colspan="145" class="timeContent">
              <div v-for="(item, index) in timeStr" v-show="item.length" :key="index">
                <strong
                  ><span>{{ item }}</span></strong
                >
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
 
<script>
import { number } from 'echarts'
export default {
  name: 'timeSelect',
  props: {
    timeArr: {
      type: Array,
      default() {
        return []
        /*
        [
          {
            start:"16:50",
            end:"18:00"
          }
        ]
        */
      }
    },
    edit: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      tableHeader: [
        '00',
        '01',
        '02',
        '03',
        '04',
        '05',
        '06',
        '07',
        '08',
        '09',
        '10',
        '11',
        '12',
        '13',
        '14',
        '15',
        '16',
        '17',
        '18',
        '19',
        '20',
        '21',
        '22',
        '23'
      ],
      rowUnit: [],
      timeContent: [],
      timeSection: [],
      timeStr: [],
      beginDay: 0,
      beginTime: 0,
      downEvent: false,
      kuangObj: {
        width: 0,
        height: 0,
        top: 0,
        left: 0,
        bottom: 0,
        right: 0,
        oldLeft: 0,
        oldTop: 0,
        flag: false
      },
      timeQuery: []
    }
  },
  computed: {
    downBoo() {
      return this.edit && this.downEvent
    }
  },
  created() {
    this.init()
  },
  watch: {
    timeArr(newVal) {
      this.infoEcho()
    }
  },
  mounted() {},
  methods: {
    init() {
      let arr = []
      for (let j = 0; j < 24 * 6; j++) {
        arr.push({ class: null, timeData: j, hoverClass: null })
      }
      this.rowUnit = arr
      this.timeContent = { arr: [] }
      this.timeSection = []
      this.timeStr.push('')
      this.infoEcho()
    },
    infoEcho() {
      if (this.timeArr.length) {
        this.timeArr.forEach(item => {
          let startArr = item.start.split(':')
          let endArr = item.end.split(':')
          let start = (startArr[0] * 6 + startArr[1] / 10).toFixed(0) - 0
          let end = (endArr[0] * 6 + endArr[1] / 10).toFixed(0) - 1
          for (let y = start; y < end + 1; y++) {
            if (this.rowUnit[y].class == null) {
              this.rowUnit[y].class = 'ui-selected'
              this.timeContent.arr.push(this.rowUnit[y].timeData)
            }
          }
        })
        this.filterTime()
      }
    },
    handleMouseDown(i, day) {
      this.downEvent = true
      this.beginDay = day
      this.beginTime = i
    },
    handleMouseOver(i) {
      if (this.downBoo) {
        let _this = this
        let begin = this.beginTime
        let start = begin <= i ? begin : i //x轴 起点
        let length = Math.abs(begin - i)
        let end = start + length //x轴 终点
        this.clearHoverClass()
        for (let y = start; y < end + 1; y++) {
          this.rowUnit[y].hoverClass = 'ui-hovers'
        }
      }
    },
    clearHoverClass() {
      this.rowUnit.forEach(item => {
        item.hoverClass = null
      })
    },
    handleMouseUp(i, day) {
      //当点击事件是在table内才触发选取数据操作
      if (this.downBoo) {
        //选时间段
        this.clearHoverClass()
        let _this = this
        let begin = this.beginTime
        let start = begin <= i ? begin : i //x轴 起点
        let length = Math.abs(begin - i)
        let end = start + length //x轴 终点
        //当框选范围内所有块都是选中状态时,执行反选
        function isAdd() {
          for (let y = start; y < end + 1; y++) {
            if (_this.rowUnit[y].class == null) return true
          }
          return false
        }
 
        if (isAdd()) {
          //没选中的全都选上
          for (let y = start; y < end + 1; y++) {
            if (this.rowUnit[y].class == null) {
              this.rowUnit[y].class = 'ui-selected'
              this.timeContent.arr.push(this.rowUnit[y].timeData)
            }
          }
        } else {
          //反选
          for (let y = start; y < end + 1; y++) {
            this.rowUnit[y].class = null
            this.timeContent.arr = this.timeContent.arr.filter(item => item != this.rowUnit[y].timeData)
          }
        }
      }
      //过滤时间段,将临近的时间段合并
      this.filterTime()
      this.downEvent = false
    },
    filterTime(start, end) {
      let _this = this
      //选中的x,y坐标信息 x:0-47  y:0-6
      function sortCut(arr) {
        //提取连续的数字
        var result = []
        arr.forEach(function(v, i) {
          var temp = result[result.length - 1]
          if (!i) {
            result.push([v])
          } else if (v % 1 === 0 && v - temp[temp.length - 1] == 1) {
            temp.push(v)
          } else {
            result.push([v])
          }
        })
        return result
      }
      function timeToStr(arr) {
        //把数组转成方便人看到字符串
        let str = ''
        let array = []
        arr.forEach((arr, index) => {
          let str1 = ''
          if (index == 0) {
            str1 = _this.toStr(arr[0]) + '~' + _this.toStr(arr[1])
          } else {
            str1 = ' , ' + _this.toStr(arr[0]) + '~' + _this.toStr(arr[1])
          }
          array.push({
            start: _this.toStr(arr[0]),
            end: _this.toStr(arr[1])
          })
          str += str1
        })
        _this.timeQuery = array
        return str
      }
      //排序,分割成
      let arr1 = sortCut(this.timeContent.arr.sort((a, b) => a - b))
      let arr2 = []
      arr1.forEach(arr => {
        //转成带小数点的时间段,以及供前端显示的字符串
        let arr3 = []
        arr3.push(arr[0] / 6)
        arr3.push(arr[arr.length - 1] / 6 + 1 / 6)
        arr2.push(arr3)
      })
      //console.log(arr2)
      this.timeStr[0] = timeToStr(arr2)
      this.timeSection = arr2
      // }
    },
    toStr(num) {
      if (Number.isInteger(num)) {
        let str = num < 10 ? '0' + num : num.toString()
        return str + ':00'
      } else {
        let str = Math.floor(num) < 10 ? '0' + Math.floor(num) : Math.floor(num).toString()
        return str + ':' + ((num % 1) * 60).toFixed(0)
      }
    },
    clear() {
      this.rowUnit.forEach(item => {
        item.class = null
      })
      this.timeContent.arr = []
      this.timeSection.length = 0
      this.timeStr.length = 0
      for (let i = 0; i < 7; i++) {
        this.timeStr.push('')
      }
      this.timeQuery = []
    }
  }
}
</script>
 
<style scoped>
.byted-weektime .calendar {
  -webkit-user-select: none;
  position: relative;
  display: inline-block;
}
/*.byted-weektime .calendar .schedule{background:#2F88FF;width:0;height:0;position:fixed;display:none;top:0;left:0;pointer-events:none;-webkit-transition:all 400ms ease;-moz-transition:all 400ms ease;-ms-transition:all 400ms ease;transition:all 400ms ease}*/
.byted-weektime .calendar .calendar-table {
  border-collapse: collapse;
  border-radius: 4px;
}
.byted-weektime .calendar .calendar-table tr .calendar-atom-time:hover {
  background: #ccc;
}
.byted-weektime .calendar .calendar-table tr .ui-selected {
  background: #2f88ff !important;
}
.byted-weektime .calendar .calendar-table tr .ui-hovers {
  background: #6e9dda !important;
}
.byted-weektime .calendar .calendar-table tr .ui-selected:hover {
  background: #064ca7 !important;
}
.byted-weektime .calendar .calendar-table tr,
.byted-weektime .calendar .calendar-table td,
.byted-weektime .calendar .calendar-table th {
  border: 1px solid #ccc;
  font-size: 12px;
  text-align: center;
  min-width: 3px;
  line-height: 1.8em;
  -webkit-transition: background 200ms ease;
  -moz-transition: background 200ms ease;
  -ms-transition: background 200ms ease;
  transition: background 200ms ease;
}
.byted-weektime .calendar .calendar-table tbody tr {
  height: 30px;
}
.byted-weektime .calendar .calendar-table tbody tr td:first-child {
  background: #f8f9fa;
}
.byted-weektime .calendar .calendar-table thead th,
.byted-weektime .calendar .calendar-table thead td {
  background: #f8f9fa;
}
.byted-weektime .calendar .calendar-table .td-table-tip {
  line-height: 2.4em;
  padding: 0 12px 0 19px;
  background: #fff !important;
}
.byted-weektime .calendar .calendar-table .td-table-tip .clearfix {
  height: 46px;
  line-height: 46px;
}
.byted-weektime .calendar .calendar-table .td-table-tip .pull-left {
  font-size: 14px;
  color: #333333;
}
.byted-weektime .week-td {
  width: 75px;
  padding: 20px 0;
}
.byted-weektime a {
  cursor: pointer;
  color: #2f88ff;
  font-size: 14px;
}
#kuang {
  position: absolute;
  background-color: blue;
  opacity: 0.3;
}
</style>