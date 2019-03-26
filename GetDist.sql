DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `GetDist`(`lat_a` DOUBLE, `long_a` DOUBLE, `lat_b` DOUBLE, `long_b` DOUBLE) RETURNS double
    DETERMINISTIC
BEGIN 
      DECLARE dist double;
      DECLARE dLat double;
      DECLARE dLong double;
      DECLARE a double;
      DECLARE an double;
      DECLARE c double;
      
      SET lat_a = RADIANS(lat_a);
      SET long_a = RADIANS(long_a);
      SET lat_b = RADIANS(lat_b);
      SET long_b = RADIANS(long_b);
      
      SET dLat = lat_a -lat_b;
      SET dLong = long_a - long_b;
      
      SET a = power(sin(dLat/2), 2) + cos(lat_a) * cos(lat_b) * power(sin(dLong/2), 2);
      SET an = 1-a;
	  SET c = 2 * atan2(sqrt(a), sqrt(an));
      
	  SET dist = 6371000 * c;
  
    RETURN dist; 
  END$$
DELIMITER ;