-- 2022년 4월 13일 취소되지 않은 CS 진료 예약 내역 조회
-- 예약번호, 이름, 환자번호, 코드, 의사이름, 예약일시
-- 예약일시 오름차순
SELECT APNT_NO, P.PT_NAME, A.PT_NO, A.MCDP_CD, D.DR_NAME, APNT_YMD
FROM APPOINTMENT AS A
    JOIN PATIENT AS P
    ON A.PT_NO = P.PT_NO
        JOIN DOCTOR AS D
        ON A.MDDR_ID = D.DR_ID
WHERE APNT_CNCL_YN = 'N' AND APNT_YMD LIKE '2022-04-13%' AND A.MCDP_CD = 'CS'
ORDER BY 6