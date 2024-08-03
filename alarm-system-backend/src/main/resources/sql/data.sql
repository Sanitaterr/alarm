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
