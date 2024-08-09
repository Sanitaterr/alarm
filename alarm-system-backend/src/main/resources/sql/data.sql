SET @minute_offset := 0;

INSERT INTO alarm (source, type, level, occur_time, confirm_time, recover_time, confirm_status, recover_status, additional_info, firm_id)
SELECT
    source,
    type,
    level,
    occur_time,
    confirm_time,
    recover_time,
    CASE
        WHEN confirm_time IS NULL THEN 0
        ELSE 1
        END AS confirm_status,
    CASE
        WHEN recover_time IS NULL THEN 0
        ELSE 1
        END AS recover_status,
    additional_info,
    firm_id
FROM (
         SELECT
             -- 使用 MD5 生成随机字符串作为 source
             CONCAT(
                     SUBSTRING(MD5(RAND()), 1, 8), '-',
                     SUBSTRING(MD5(RAND()), 9, 4), '-',
                     SUBSTRING(MD5(RAND()), 13, 4), '-',
                     SUBSTRING(MD5(RAND()), 17, 4), '-',
                     SUBSTRING(MD5(RAND()), 21, 12)
             ) AS source,
             type,
             FLOOR(1 + RAND() * 5) AS level,
             DATE_ADD(
                     '2024-07-27 00:00:00',
                     INTERVAL FLOOR(RAND() * 10000) MINUTE
             ) AS base_time,
             DATE_ADD(
                     DATE_ADD('2024-07-27 00:00:00', INTERVAL FLOOR(RAND() * 10000) MINUTE),
                     INTERVAL FLOOR(RAND() * 60) SECOND
             ) + INTERVAL FLOOR(RAND() * 1000000) MICROSECOND AS occur_time,
             CASE
                 WHEN RAND() < 0.5 THEN NULL
                 ELSE DATE_ADD(
                              DATE_ADD('2024-07-27 00:00:00', INTERVAL FLOOR(RAND() * 10000) MINUTE),
                              INTERVAL FLOOR(RAND() * 60) SECOND
                      ) + INTERVAL FLOOR(RAND() * 1000) MICROSECOND
                 END AS confirm_time,
             CASE
                 WHEN RAND() < 0.5 THEN NULL
                 ELSE DATE_ADD(
                              DATE_ADD('2024-07-27 00:00:00', INTERVAL FLOOR(RAND() * 10000) MINUTE),
                              INTERVAL FLOOR(RAND() * 60) SECOND
                      ) + INTERVAL FLOOR(RAND() * 1000) MICROSECOND
                 END AS recover_time,
             NULL AS additional_info,
             FLOOR(1 + RAND() * 10) AS firm_id
         FROM (
                  SELECT '电源电压不稳定' AS type UNION ALL
                  SELECT '设备故障' UNION ALL
                  SELECT '温度过高' UNION ALL
                  SELECT '湿度过高' UNION ALL
                  SELECT '压力异常'
              ) AS types
                  CROSS JOIN (
             SELECT 1 AS n UNION ALL
             SELECT 2 UNION ALL
             SELECT 3 UNION ALL
             SELECT 4 UNION ALL
             SELECT 5 UNION ALL
             SELECT 6 UNION ALL
             SELECT 7 UNION ALL
             SELECT 8 UNION ALL
             SELECT 9 UNION ALL
             SELECT 10
         ) AS numbers
         LIMIT 100
     ) AS generated_data;




INSERT INTO alarm (source, firm_id)
VALUES
    ('泊森有限公司一楼生产车间01号', FLOOR(1 + RAND() * 10)),
    ('泊森有限公司二楼仓库02号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司一楼纺织车间03号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司三楼原料库04号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司主厂区05号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司二厂区06号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司办公大楼07号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司化工车间08号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司冶炼车间09号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司炼钢车间10号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司总装车间11号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司成品库12号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司锅炉房13号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司控制室14号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线A区15号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线B区16号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司整车装配车间17号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司发动机装配车间18号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司码头区19号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司仓储区20号', FLOOR(1 + RAND() * 10)),
    -- 重复上述结构，生成100条数据
    ('泊森有限公司一楼生产车间21号', FLOOR(1 + RAND() * 10)),
    ('泊森有限公司二楼仓库22号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司一楼纺织车间23号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司三楼原料库24号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司主厂区25号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司二厂区26号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司办公大楼27号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司化工车间28号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司冶炼车间29号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司炼钢车间30号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司总装车间31号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司成品库32号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司锅炉房33号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司控制室34号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线A区35号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线B区36号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司整车装配车间37号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司发动机装配车间38号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司码头区39号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司仓储区40号', FLOOR(1 + RAND() * 10)),
    -- 重复上述结构，生成100条数据
    ('泊森有限公司一楼生产车间41号', FLOOR(1 + RAND() * 10)),
    ('泊森有限公司二楼仓库42号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司一楼纺织车间43号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司三楼原料库44号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司主厂区45号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司二厂区46号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司办公大楼47号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司化工车间48号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司冶炼车间49号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司炼钢车间50号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司总装车间51号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司成品库52号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司锅炉房53号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司控制室54号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线A区55号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线B区56号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司整车装配车间57号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司发动机装配车间58号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司码头区59号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司仓储区60号', FLOOR(1 + RAND() * 10)),
    -- 重复上述结构，生成100条数据
    ('泊森有限公司一楼生产车间61号', FLOOR(1 + RAND() * 10)),
    ('泊森有限公司二楼仓库62号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司一楼纺织车间63号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司三楼原料库64号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司主厂区65号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司二厂区66号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司办公大楼67号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司化工车间68号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司冶炼车间69号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司炼钢车间70号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司总装车间71号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司成品库72号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司锅炉房73号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司控制室74号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线A区75号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线B区76号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司整车装配车间77号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司发动机装配车间78号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司码头区79号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司仓储区80号', FLOOR(1 + RAND() * 10)),
    -- 重复上述结构，生成100条数据
    ('泊森有限公司一楼生产车间81号', FLOOR(1 + RAND() * 10)),
    ('泊森有限公司二楼仓库82号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司一楼纺织车间83号', FLOOR(1 + RAND() * 10)),
    ('杭州百隆纺织有限公司三楼原料库84号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司主厂区85号', FLOOR(1 + RAND() * 10)),
    ('浙江华友钴业股份有限公司二厂区86号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司办公大楼87号', FLOOR(1 + RAND() * 10)),
    ('中石化宁波工程有限公司化工车间88号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司冶炼车间89号', FLOOR(1 + RAND() * 10)),
    ('杭州钢铁集团有限公司炼钢车间90号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司总装车间91号', FLOOR(1 + RAND() * 10)),
    ('浙江正泰电器股份有限公司成品库92号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司锅炉房93号', FLOOR(1 + RAND() * 10)),
    ('杭州市热电集团有限公司控制室94号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线A区95号', FLOOR(1 + RAND() * 10)),
    ('杭州娃哈哈集团有限公司生产线B区96号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司整车装配车间97号', FLOOR(1 + RAND() * 10)),
    ('浙江吉利控股集团有限公司发动机装配车间98号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司码头区99号', FLOOR(1 + RAND() * 10)),
    ('宁波港股份有限公司仓储区100号', FLOOR(1 + RAND() * 10));


UPDATE alarm_particulars ap
    JOIN (
        SELECT id AS alarm_id, source
        FROM alarm
        WHERE id BETWEEN 1 AND 100
    ) a
    ON ap.alarm_id = a.alarm_id
SET ap.source = a.source
WHERE ap.alarm_id BETWEEN 1 AND 100;
