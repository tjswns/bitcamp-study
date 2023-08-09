-- myapp_member 테이블 예제 데이터
insert into myapp_member(member_no, name, email, password, age, gender, top, pants, shoes) 
  values(1, 'aaa', 'aaa@test.com', sha1('1111'),'26', 'W', 'M', '26', '240');
insert into myapp_member(member_no, name, email, password, age, gender, top, pants, shoes) 
  values(2, 'bbb', 'bbb@test.com', sha1('1111'),'27', 'M', 'M', '30', '270');
insert into myapp_member(member_no, name, email, password, age, gender, top, pants, shoes) 
  values(3, 'ccc', 'ccc@test.com', sha1('1111'),'23', 'W', 'L', '24', '245');
insert into myapp_member(member_no, name, email, password, age, gender, top, pants, shoes) 
  values(4, 'ddd', 'ddd@test.com', sha1('1111'),'25', 'M', 'XL', '32', '270');
insert into myapp_member(member_no, name, email, password, age, gender, top, pants, shoes) 
  values(5, 'eee', 'eee@test.com', sha1('1111'),'21', 'W', 'S', '26', '250');
insert into myapp_member(member_no, name, email, password, age, gender, top, pants, shoes) 
  values(6, 'fff', 'fff@test.com', sha1('1111'), '22', 'M', 'L', '28', '270');

-- myapp_acc 테이블 예제 데이터
insert into myapp_acc(acc_no, style, choose, size, writer, password, category)
  values(11, '데일리', '목걸이', '9호', 1, sha1('1111'), 1);
insert into myapp_acc(acc_no, style, choose, size, writer, password, category)
  values(12, '힙', '반지', '10호', 2, sha1('1111'), 1);
insert into myapp_acc(acc_no, style, choose, size, writer, password, category)
  values(13, '스트릿', '반지', '11호', 3, sha1('1111'), 1);
insert into myapp_acc(acc_no, style, choose, size, writer, password, category)
  values(14, '데일리', '안경', '11호', 3, sha1('1111'), 1);
insert into myapp_acc(acc_no, style, choose, size, writer, password, category)
  values(15, '스트릿', '목걸이', '9호', 4, sha1('1111'), 2);
  
  -- myapp_styling 테이블 예제 데이터
insert into myapp_styling(styling_no, style, brand, fit, writer, password, category)
  values(16, '데일리', '구찌', '오버 핏', 1, sha1('1111'), 1);
insert into myapp_styling(styling_no, style, brand, fit, writer, password, category)
  values(17, '파티', '프라다', '슬림 핏', 2, sha1('1111'), 1);
insert into myapp_styling(styling_no, style, brand, fit, writer, password, category)
  values(18, '데이트', '샤넬', '스텐다드 핏', 2, sha1('1111'), 1);
insert into myapp_styling(styling_no, style, brand, fit, writer, password, category)
  values(19, '데일리', '구찌', '슬림 핏', 3, sha1('1111'), 1);
insert into myapp_styling(styling_no, style, brand, fit, writer, password, category)
  values(20, '파티', '샤넬', '오버 핏', 4, sha1('1111'), 2);
