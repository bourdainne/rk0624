
INSERT INTO brand (name) VALUES ('Stihl'),('Werner'),('DeWalt'),('Ridgid');

INSERT INTO tool_details (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES
('Ladder',1.99,true,true,false),('Chainsaw',1.49,true,false,true),('Jackhammer',2.99,true,false,false);

INSERT INTO tool (tool_code,tool_details_id,brand_id) VALUES
('CHNS',(SELECT id FROM tool_details WHERE tool_type = 'Chainsaw'),(SELECT id FROM brand WHERE name = 'Stihl')),
('LADW',(SELECT id FROM tool_details WHERE tool_type = 'Ladder'),(SELECT id FROM brand WHERE name = 'Werner')),
('JAKD',(SELECT id FROM tool_details WHERE tool_type = 'Jackhammer'),(SELECT id FROM  brand WHERE name = 'DeWalt')),
('JAKR',(SELECT id FROM tool_details WHERE tool_type = 'Jackhammer'),(SELECT id FROM brand WHERE name = 'Ridgid'));

INSERT INTO holiday (name, is_fixed, day_of_month, month_of_year, day_of_week, earliest_day, latest_day) VALUES
('INDEPENDENCEDAY',true,4,7,null,null,null),('LABORDAY',false,null,9,1,1,6);