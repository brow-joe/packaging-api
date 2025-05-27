-- 1 - Consulta: Carga horária semanal por professor
SELECT
    p.id AS professor_id,
    p.name AS professor_name,
    SUM(EXTRACT(EPOCH FROM (cs.end_time - cs.start_time)) / 3600) AS total_hours_per_week
FROM
    professor p, 
	class c, 
	class_schedule cs
WHERE c.professor_id = p.id
	AND cs.class_id = c.id
GROUP BY 
	p.id, 
	p.name
ORDER BY 
	p.id;

-- 2 - Consulta: Lista de salas com horários livres e ocupados
SELECT
    r.id AS room_id,
    r.name AS room_name,
    b.name AS building_name,
    cs.day_of_week,
    cs.start_time,
    cs.end_time,
    'Ocupado' AS status
FROM room r
JOIN building b 
	ON b.id = r.building_id
LEFT JOIN class_schedule cs 
	ON cs.room_id = r.id
ORDER BY 
	r.id, 
	cs.day_of_week, 
	cs.start_time;
