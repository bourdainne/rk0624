
CREATE TABLE IF NOT EXISTS brand (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tool_details (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    tool_type VARCHAR(50) NOT NULL UNIQUE,
    daily_charge NUMERIC(19,4) NOT NULL,
    weekday_charge BOOLEAN NOT NULL DEFAULT TRUE,
    weekend_charge BOOLEAN NOT NULL,
    holiday_charge BOOLEAN NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'USD'
);

CREATE TABLE IF NOT EXISTS tool (
    id INT PRIMARY KEY AUTO_INCREMENT not null,
    tool_code varchar(10) not null unique,
    tool_details_id int not null references tool_details(id),
    brand_id INT NOT NULL REFERENCES brand(id)
);

CREATE TABLE IF NOT EXISTS holiday (
     id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
     name varchar(50) NOT NULL UNIQUE,
     is_fixed BOOLEAN NOT NULL DEFAULT TRUE,
     day_of_month SMALLINT,
     month_of_year SMALLINT,
     day_of_week SMALLINT,
     earliest_day SMALLINT,
     latest_day SMALLINT
);

ALTER TABLE holiday ADD CONSTRAINT holiday_constraint check
(
    is_fixed = TRUE AND day_of_month IS NOT NULL AND month_of_year IS NOT NULL AND day_of_week IS NULL AND earliest_day IS NULL AND latest_day IS NULL
    OR is_fixed = FALSE AND day_of_month IS NULL AND month_of_year IS NOT NULL AND day_of_week IS NOT NULL AND earliest_day IS NOT NULL AND latest_day IS NOT NULL
);

