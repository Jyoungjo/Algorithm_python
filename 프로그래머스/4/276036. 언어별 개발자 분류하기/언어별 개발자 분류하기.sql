SELECT G.GRADE, D.ID, D.EMAIL
FROM DEVELOPERS AS D
    JOIN (
        SELECT ID,
               CASE
               WHEN EXISTS(
                   SELECT 1
                   FROM SKILLCODES
                   WHERE CATEGORY = 'Front End' AND CODE & SKILL_CODE
               )
                 AND SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python') THEN 'A'
               WHEN SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#') THEN 'B'
               WHEN EXISTS(
                   SELECT 1
                   FROM SKILLCODES
                   WHERE CATEGORY = 'Front End' AND CODE & SKILL_CODE
               ) THEN 'C'
               ELSE NULL END AS GRADE
        FROM DEVELOPERS
    ) AS G
    ON G.ID = D.ID
WHERE G.GRADE IS NOT NULL
ORDER BY 1, 2