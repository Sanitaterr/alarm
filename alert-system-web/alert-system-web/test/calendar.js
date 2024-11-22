//创建日历插件构造函数
function CalendarPlugin(elem,opt={}){
  //把文本框设置为日历对象的属性
  this.eInput = elem;
  //执行初始化
  this.init();
}

//初始化
CalendarPlugin.prototype.init = function(){
  //获取原有intpu元素的父元素
  var eInputParent = this.eInput.parentNode;
  //创建日历包裹元素并添加class名称
  var eWrap = document.createElement('div');
  eWrap.className = 'calendar_wrap';
  //创建文本框容器元素
  var eInputContainer = document.createElement('div');
  eInputContainer.className = 'calendar_container';
  //创建清除按钮
  var eClear = document.createElement('div');
  eClear.className = 'calendar_clean';
  //创建日历图标元素
  var eIcon = document.createElement('div');    
  eIcon.className = 'calendar_icon';
  //把日历包裹放到原有父元素中
  eInputParent.appendChild(eWrap);
  //文本框容器放到包裹中
  eWrap.appendChild(eInputContainer);
  //把相关元素放到文本框容器中
  eInputContainer.appendChild(this.eInput);
  eInputContainer.appendChild(eClear);
  eInputContainer.appendChild(eIcon);

  //设置文本框为只读
  this.eInput.setAttribute('readonly',true);
  
   //创建主要日历容器元素
   this.eMain = document.createElement('div');
   this.eMain.className = 'calendar_main';
   //把日历容器放到包裹元素中
   eWrap.appendChild(this.eMain);

   //创建日历头部
   this.eHead = document.createElement('div');
   this.eHead.className = 'calendar_head';
   //把日历头部放到日历容器中
   this.eMain.appendChild(this.eHead);
   //设置当前年份
   this.nYear = null;
   //设置当前月份
   this.nMonth = null;
   //设置日历模式，默认显示日历
   this.sModel = 'date';
       
   //创建日历主体
   this.eBody = document.createElement('div');
   this.eBody.className = 'calendar_body';
   //把日历主体放到日历容器中
   this.eMain.appendChild(this.eBody);
   //当前选定日期
   this.selDate = null;

   //创建底部元素
   this.eFoot = document.createElement('div');
   this.eFoot.className = 'calendar_foot';
   this.eDefine = document.createElement('button');
   this.eDefine.className = 'define_btn';
   //把底部元素放到日历容器中
   this.eMain.appendChild(this.eFoot);
   this.eFoot.appendChild(this.eDefine);
   this.eDefine.innerHTML = '今 天';

   //生成日历元素
  this.generateDate();
}

//创建头部元素
CalendarPlugin.prototype.generateHead = function(){
    //根据日历模式不同，组成日历头部html代码
    var sHeadHtml = '<a class="left_year_btn chang_btn" data-run="lessYear"></a>';
    
    if(this.sModel == 'date'){    //日历模式
        sHeadHtml += `<a class="left_month_btn chang_btn" data-run="lessMonth"></a>
                                    <a class="year_btn" data-run="showYear" data-model="year">${this.nYear}年</a>
                                    <a class="month_btn" data-run="showMonth" data-model="month">${+this.nMonth+1}月</a>
                                    <a class="right_month_btn chang_btn" data-run="addMonth"></a>`;
    }else if(this.sModel == 'month'){    //月历模式
        sHeadHtml += `<a class="year_month_btn" data-run="showYear" data-model="year">${this.nYear}年</a>`;
    }else if (this.sModel == 'year'){    //年历模式
        sHeadHtml += `<a class="year_month_btn">${+(Math.floor(this.nYear/10)+'0')}-
                                    ${+(Math.floor(this.nYear/10)+'0')+10}</a>`;
    }

    sHeadHtml += '<a class="right_year_btn chang_btn" data-run="addYear"></a>';
    //填充日历头部
    this.eHead.innerHTML = sHeadHtml;
}
//生成日历
CalendarPlugin.prototype.generateDate = function(date=null){
    //组成日历的html代码
    var sBodyHtml = '<table>';
    //组合周日 - 周六的列表头
    sBodyHtml += `<thead>
                                    <tr><th>日</th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr>
                             </thead>`

    /*日历中需要记录当前日期、选定日期、面板日期共三个时间*/
    //当前日期：当前日期在日历面板中有一个背景和加粗字体，需要添加class为cur_day
    var dCurDate = new Date();
    //选定日期：在面板上选择的日期，并且显示在文本框中，有一个背景和白色字体，需要添加class为sel_day
    //选定日期需要记录在日历实例的selDate属性上，如果暂无选定日期则为当前日期
    var dSelDate = this.selDate || dCurDate;
    //初始化当前年/月
    this.nYear = this.nYear || dSelDate.getFullYear();
    this.nMonth = this.nMonth || dSelDate.getMonth();
    //面板上显示的日历
    var dShowDate = new Date(this.nYear,this.nMonth,dSelDate.getDate());

    /*
    日历面板规则：
    显示42天，6行7列；
    面板上的第一天是当月1号往前推到星期日。比如当月1号是星期一则上月显示1天、星期三上月显示3天、星期日上月显示7天；
    */
    //计算上月要显示的天数
    var nFrontNum = new Date(this.nYear,this.nMonth,1).getDay() || 7;
    //日历面板上的日期每增加一个循环周期是一天，获取一天的毫秒数
    var cycle = 1000*60*60*24;
    sBodyHtml += '<thbody>'
    //循环42次
    for(let i=1;i<43;i++){
        //以下公式获取日历中每天递增日期的时间戳
        let dTimes = +dShowDate + cycle * (i-nFrontNum-dShowDate.getDate());
        //通过时间戳创建Date实例对象
        let dNewDate = new Date(dTimes);
        //获取日期添加到html中
        if((i-1)%7==0){ //判断是否需要换行
            sBodyHtml += '<tr>';
        }
        //判断是否是选定日期，当前日期，面板当月日期，分别加上对应的class
        sBodyHtml += `<td><a data-time="${dTimes}" class="${
                                    this.quiteDate(dNewDate,dSelDate)?'sel_day':
                                    this.quiteDate(dNewDate,dCurDate)?'cur_day':
                                    dNewDate.getMonth()==this.nMonth?'cur_month':''
                                 }">${dNewDate.getDate()}</a></td>`;
        if(i%7==0){ //判断是否需要结束表格行
            sBodyHtml += '</tr>';
        }
    }
    sBodyHtml += '</thbody></table>';

    //填充日历面板
    this.eBody.innerHTML = sBodyHtml;
    //生成面板头部
    this.generateHead();
}
//比较两个日期是否为同一天
CalendarPlugin.prototype.quiteDate = function(d1,d2){
    var format = 'yyyy-MM-dd';
    return this.format(d1,format) == this.format(d2,format);
}
//格式化日期
CalendarPlugin.prototype.format = function(date,format){
    //用于正则表达式的匹配
    var o =  {
    "M+" : date.getMonth()+1, //月
    "d+" : date.getDate(), //日
    "h+" : date.getHours(), //小时
    "m+" : date.getMinutes(), //分钟
    "s+" : date.getSeconds(), //秒
    };
    //使用正则将yyyy替换为当前年份
    if(/(y+)/.test(format)){
        format = format.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    //枚举o对象中匹配的正则，比如MM替换当前月份，dd替换为当前日期
    for(var k in o)  {
        if(new RegExp("("+ k +")").test(format)){
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    //把格式替换为正确日期后返回
    return format;
}

