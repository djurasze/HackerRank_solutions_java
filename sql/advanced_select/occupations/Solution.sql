--Pivot the Occupation column in OCCUPATIONS so that each Name is sorted alphabetically and displayed underneath its corresponding Occupation. The output column headers should be Doctor, Professor, Singer, and Actor, respectively.
--
--Note: Print NULL when there are no more names corresponding to an occupation.


SELECT doctor, professor, singer, actor FROM
(
    SELECT NAME, OCCUPATION, ROW_NUMBER() OVER (PARTITION BY OCCUPATION ORDER BY NAME) AS row_num FROM OCCUPATIONS
)
PIVOT
(
    MIN(NAME)
    FOR OCCUPATION IN ( 'Doctor' as doctor, 'Professor' as professor, 'Singer' as singer , 'Actor' as actor)
) ORDER BY row_num;