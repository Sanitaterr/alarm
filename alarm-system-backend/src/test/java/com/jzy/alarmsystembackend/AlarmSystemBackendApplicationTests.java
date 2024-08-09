package com.jzy.alarmsystembackend;

import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.pojo.DO.AlarmParticulars;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class AlarmSystemBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;
    @Test
    void test() {
        List<String> sources = new ArrayList<>();

        sources.add("1,泊森有限公司一楼生产车间01号");
        sources.add("2,泊森有限公司二楼仓库02号");
        sources.add("3,杭州百隆纺织有限公司一楼纺织车间03号");
        sources.add("4,杭州百隆纺织有限公司三楼原料库04号");
        sources.add("5,浙江华友钴业股份有限公司主厂区05号");
        sources.add("6,浙江华友钴业股份有限公司二厂区06号");
        sources.add("7,中石化宁波工程有限公司办公大楼07号");
        sources.add("8,中石化宁波工程有限公司化工车间08号");
        sources.add("9,杭州钢铁集团有限公司冶炼车间09号");
        sources.add("10,杭州钢铁集团有限公司炼钢车间10号");
        sources.add("11,浙江正泰电器股份有限公司总装车间11号");
        sources.add("12,浙江正泰电器股份有限公司成品库12号");
        sources.add("13,杭州市热电集团有限公司锅炉房13号");
        sources.add("14,杭州市热电集团有限公司控制室14号");
        sources.add("15,杭州娃哈哈集团有限公司生产线A区15号");
        sources.add("16,杭州娃哈哈集团有限公司生产线B区16号");
        sources.add("17,浙江吉利控股集团有限公司整车装配车间17号");
        sources.add("18,浙江吉利控股集团有限公司发动机装配车间18号");
        sources.add("19,宁波港股份有限公司码头区19号");
        sources.add("20,宁波港股份有限公司仓储区20号");
        sources.add("21,泊森有限公司一楼生产车间21号");
        sources.add("22,泊森有限公司二楼仓库22号");
        sources.add("23,杭州百隆纺织有限公司一楼纺织车间23号");
        sources.add("24,杭州百隆纺织有限公司三楼原料库24号");
        sources.add("25,浙江华友钴业股份有限公司主厂区25号");
        sources.add("26,浙江华友钴业股份有限公司二厂区26号");
        sources.add("27,中石化宁波工程有限公司办公大楼27号");
        sources.add("28,中石化宁波工程有限公司化工车间28号");
        sources.add("29,杭州钢铁集团有限公司冶炼车间29号");
        sources.add("30,杭州钢铁集团有限公司炼钢车间30号");
        sources.add("31,浙江正泰电器股份有限公司总装车间31号");
        sources.add("32,浙江正泰电器股份有限公司成品库32号");
        sources.add("33,杭州市热电集团有限公司锅炉房33号");
        sources.add("34,杭州市热电集团有限公司控制室34号");
        sources.add("35,杭州娃哈哈集团有限公司生产线A区35号");
        sources.add("36,杭州娃哈哈集团有限公司生产线B区36号");
        sources.add("37,浙江吉利控股集团有限公司整车装配车间37号");
        sources.add("38,浙江吉利控股集团有限公司发动机装配车间38号");
        sources.add("39,宁波港股份有限公司码头区39号");
        sources.add("40,宁波港股份有限公司仓储区40号");
        sources.add("41,泊森有限公司一楼生产车间41号");
        sources.add("42,泊森有限公司二楼仓库42号");
        sources.add("43,杭州百隆纺织有限公司一楼纺织车间43号");
        sources.add("44,杭州百隆纺织有限公司三楼原料库44号");
        sources.add("45,浙江华友钴业股份有限公司主厂区45号");
        sources.add("46,浙江华友钴业股份有限公司二厂区46号");
        sources.add("47,中石化宁波工程有限公司办公大楼47号");
        sources.add("48,中石化宁波工程有限公司化工车间48号");
        sources.add("49,杭州钢铁集团有限公司冶炼车间49号");
        sources.add("50,杭州钢铁集团有限公司炼钢车间50号");
        sources.add("51,浙江正泰电器股份有限公司总装车间51号");
        sources.add("52,浙江正泰电器股份有限公司成品库52号");
        sources.add("53,杭州市热电集团有限公司锅炉房53号");
        sources.add("54,杭州市热电集团有限公司控制室54号");
        sources.add("55,杭州娃哈哈集团有限公司生产线A区55号");
        sources.add("56,杭州娃哈哈集团有限公司生产线B区56号");
        sources.add("57,浙江吉利控股集团有限公司整车装配车间57号");
        sources.add("58,浙江吉利控股集团有限公司发动机装配车间58号");
        sources.add("59,宁波港股份有限公司码头区59号");
        sources.add("60,宁波港股份有限公司仓储区60号");
        sources.add("61,泊森有限公司一楼生产车间61号");
        sources.add("62,泊森有限公司二楼仓库62号");
        sources.add("63,杭州百隆纺织有限公司一楼纺织车间63号");
        sources.add("64,杭州百隆纺织有限公司三楼原料库64号");
        sources.add("65,浙江华友钴业股份有限公司主厂区65号");
        sources.add("66,浙江华友钴业股份有限公司二厂区66号");
        sources.add("67,中石化宁波工程有限公司办公大楼67号");
        sources.add("68,中石化宁波工程有限公司化工车间68号");
        sources.add("69,杭州钢铁集团有限公司冶炼车间69号");
        sources.add("70,杭州钢铁集团有限公司炼钢车间70号");
        sources.add("71,浙江正泰电器股份有限公司总装车间71号");
        sources.add("72,浙江正泰电器股份有限公司成品库72号");
        sources.add("73,杭州市热电集团有限公司锅炉房73号");
        sources.add("74,杭州市热电集团有限公司控制室74号");
        sources.add("75,杭州娃哈哈集团有限公司生产线A区75号");
        sources.add("76,杭州娃哈哈集团有限公司生产线B区76号");
        sources.add("77,浙江吉利控股集团有限公司整车装配车间77号");
        sources.add("78,浙江吉利控股集团有限公司发动机装配车间78号");
        sources.add("79,宁波港股份有限公司码头区79号");
        sources.add("80,宁波港股份有限公司仓储区80号");
        sources.add("81,泊森有限公司一楼生产车间81号");
        sources.add("82,泊森有限公司二楼仓库82号");
        sources.add("83,杭州百隆纺织有限公司一楼纺织车间83号");
        sources.add("84,杭州百隆纺织有限公司三楼原料库84号");
        sources.add("85,浙江华友钴业股份有限公司主厂区85号");
        sources.add("86,浙江华友钴业股份有限公司二厂区86号");
        sources.add("87,中石化宁波工程有限公司办公大楼87号");
        sources.add("88,中石化宁波工程有限公司化工车间88号");
        sources.add("89,杭州钢铁集团有限公司冶炼车间89号");
        sources.add("90,杭州钢铁集团有限公司炼钢车间90号");
        sources.add("91,浙江正泰电器股份有限公司总装车间91号");
        sources.add("92,浙江正泰电器股份有限公司成品库92号");
        sources.add("93,杭州市热电集团有限公司锅炉房93号");
        sources.add("94,杭州市热电集团有限公司控制室94号");
        sources.add("95,杭州娃哈哈集团有限公司生产线A区95号");
        sources.add("96,杭州娃哈哈集团有限公司生产线B区96号");
        sources.add("97,浙江吉利控股集团有限公司整车装配车间97号");
        sources.add("98,浙江吉利控股集团有限公司发动机装配车间98号");
        sources.add("99,宁波港股份有限公司码头区99号");
        sources.add("100,宁波港股份有限公司仓储区100号");

        Random random = new Random();
        List<AlarmParticulars> alarmParticulars = alarmParticularsMapper.selectList(null);
        for (AlarmParticulars alarmParticular : alarmParticulars) {
            String s = sources.get(random.nextInt(99));
            String[] split = s.split(",");
            alarmParticular.setAlarmId(Integer.parseInt(split[0]));
            alarmParticular.setSource(split[1]);
            alarmParticularsMapper.updateById(alarmParticular);
        }
    }
}
