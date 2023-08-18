-- 은행
DROP TABLE IF EXISTS p_bank RESTRICT;

-- 회원정보
DROP TABLE IF EXISTS p_user_data RESTRICT;

-- 사이즈유형
DROP TABLE IF EXISTS p_size_data RESTRICT;

-- 회원사이즈
DROP TABLE IF EXISTS p_size RESTRICT;

-- 스타일
DROP TABLE IF EXISTS p_style RESTRICT;

-- 스타일사진
DROP TABLE IF EXISTS p_style_photo RESTRICT;

-- 스타일사이즈
DROP TABLE IF EXISTS p_style_size RESTRICT;

-- 은행
CREATE TABLE p_bank (
  bno       INTEGER     NOT NULL COMMENT '은행번호', -- 은행번호
  bank_name VARCHAR(50) NOT NULL COMMENT '은행명' -- 은행명
)
COMMENT '은행';

-- 은행
ALTER TABLE p_bank
  ADD CONSTRAINT PK_p_bank -- 은행 기본키
  PRIMARY KEY (
  bno -- 은행번호
  );

-- 은행 유니크 인덱스
CREATE UNIQUE INDEX UIX_p_bank
  ON p_bank ( -- 은행
    bank_name ASC -- 은행명
  );

ALTER TABLE p_bank
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '은행번호';

-- 회원정보
CREATE TABLE p_user_data (
  udno      INTEGER      NOT NULL COMMENT '회원정보번호', -- 회원정보번호
  name      VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  phone     VARCHAR(30)  NOT NULL COMMENT '전화', -- 전화
  email     VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  password  VARCHAR(10)  NOT NULL COMMENT '비밀번호', -- 비밀번호
  post_code VARCHAR(10)  NULL     COMMENT '우편번호', -- 우편번호
  bas_addr  VARCHAR(255) NULL     COMMENT '기본주소', -- 기본주소
  cet_addr  VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  bno       INTEGER      NULL     COMMENT '은행번호', -- 은행번호
  acno      VARCHAR(20)  NULL     COMMENT '계좌번호' -- 계좌번호
)
COMMENT '회원정보';

-- 회원정보
ALTER TABLE p_user_data
  ADD CONSTRAINT PK_p_user_data -- 회원정보 기본키
  PRIMARY KEY (
  udno -- 회원정보번호
  );

-- 회원정보 유니크 인덱스
CREATE UNIQUE INDEX UIX_p_user_data
  ON p_user_data ( -- 회원정보
    email ASC -- 이메일
  );

ALTER TABLE p_user_data
  MODIFY COLUMN udno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원정보번호';

-- 사이즈유형
CREATE TABLE p_size_data (
  sino      INTEGER     NOT NULL COMMENT '사이즈번호', -- 사이즈번호
  item_name VARCHAR(50) NOT NULL COMMENT '항목명', -- 항목명
  unit      VARCHAR(10) NOT NULL COMMENT '단위' -- 단위
)
COMMENT '사이즈유형';

-- 사이즈유형
ALTER TABLE p_size_data
  ADD CONSTRAINT PK_p_size_data -- 사이즈유형 기본키
  PRIMARY KEY (
  sino -- 사이즈번호
  );

ALTER TABLE p_size_data
  MODIFY COLUMN sino INTEGER NOT NULL AUTO_INCREMENT COMMENT '사이즈번호';

-- 회원사이즈
CREATE TABLE p_size (
  udno INTEGER     NOT NULL COMMENT '회원정보번호', -- 회원정보번호
  sino INTEGER     NOT NULL COMMENT '사이즈번호', -- 사이즈번호
  size VARCHAR(50) NOT NULL COMMENT '크기' -- 크기
)
COMMENT '회원사이즈';

-- 회원사이즈
ALTER TABLE p_size
  ADD CONSTRAINT PK_p_size -- 회원사이즈 기본키
  PRIMARY KEY (
  udno, -- 회원정보번호
  sino  -- 사이즈번호
  );

ALTER TABLE p_size
  MODIFY COLUMN udno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원정보번호';

-- 스타일
CREATE TABLE p_style (
  stno     INTEGER     NOT NULL COMMENT '스타일번호', -- 스타일번호
  udno     INTEGER     NULL     COMMENT '회원정보번호', -- 회원정보번호
  psize_oc VARCHAR(50) NULL     COMMENT '개인사이즈공개여부' -- 개인사이즈공개여부
)
COMMENT '스타일';

-- 스타일
ALTER TABLE p_style
  ADD CONSTRAINT PK_p_style -- 스타일 기본키
  PRIMARY KEY (
  stno -- 스타일번호
  );

ALTER TABLE p_style
  MODIFY COLUMN stno INTEGER NOT NULL AUTO_INCREMENT COMMENT '스타일번호';

-- 스타일사진
CREATE TABLE p_style_photo (
  style_pno INTEGER     NOT NULL COMMENT '스타일사진번호', -- 스타일사진번호
  pfname    VARCHAR(50) NULL     COMMENT '사진파일명', -- 사진파일명
  stno      INTEGER     NULL     COMMENT '스타일번호' -- 스타일번호
)
COMMENT '스타일사진';

-- 스타일사진
ALTER TABLE p_style_photo
  ADD CONSTRAINT PK_p_style_photo -- 스타일사진 기본키
  PRIMARY KEY (
  style_pno -- 스타일사진번호
  );

ALTER TABLE p_style_photo
  MODIFY COLUMN style_pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '스타일사진번호';

-- 스타일사이즈
CREATE TABLE p_style_size (
  stno  INTEGER     NOT NULL COMMENT '스타일번호', -- 스타일번호
  sizno INTEGER     NOT NULL COMMENT '사이즈번호', -- 사이즈번호
  size2 VARCHAR(50) NULL     COMMENT '크기' -- 크기
)
COMMENT '스타일사이즈';

-- 스타일사이즈
ALTER TABLE p_style_size
  ADD CONSTRAINT PK_p_style_size -- 스타일사이즈 기본키
  PRIMARY KEY (
  stno,  -- 스타일번호
  sizno  -- 사이즈번호
  );

ALTER TABLE p_style_size
  MODIFY COLUMN stno INTEGER NOT NULL AUTO_INCREMENT COMMENT '스타일번호';

-- 회원정보
ALTER TABLE p_user_data
  ADD CONSTRAINT FK_p_bank_TO_p_user_data -- 은행 -> 회원정보
  FOREIGN KEY (
  bno -- 은행번호
  )
  REFERENCES p_bank ( -- 은행
  bno -- 은행번호
  );

-- 회원사이즈
ALTER TABLE p_size
  ADD CONSTRAINT FK_p_user_data_TO_p_size -- 회원정보 -> 회원사이즈
  FOREIGN KEY (
  udno -- 회원정보번호
  )
  REFERENCES p_user_data ( -- 회원정보
  udno -- 회원정보번호
  );

-- 회원사이즈
ALTER TABLE p_size
  ADD CONSTRAINT FK_p_size_data_TO_p_size -- 사이즈유형 -> 회원사이즈
  FOREIGN KEY (
  sino -- 사이즈번호
  )
  REFERENCES p_size_data ( -- 사이즈유형
  sino -- 사이즈번호
  );

-- 스타일
ALTER TABLE p_style
  ADD CONSTRAINT FK_p_user_data_TO_p_style -- 회원정보 -> 스타일
  FOREIGN KEY (
  udno -- 회원정보번호
  )
  REFERENCES p_user_data ( -- 회원정보
  udno -- 회원정보번호
  );

-- 스타일사진
ALTER TABLE p_style_photo
  ADD CONSTRAINT FK_p_style_TO_p_style_photo -- 스타일 -> 스타일사진
  FOREIGN KEY (
  stno -- 스타일번호
  )
  REFERENCES p_style ( -- 스타일
  stno -- 스타일번호
  );

-- 스타일사이즈
ALTER TABLE p_style_size
  ADD CONSTRAINT FK_p_size_data_TO_p_style_size -- 사이즈유형 -> 스타일사이즈
  FOREIGN KEY (
  sizno -- 사이즈번호
  )
  REFERENCES p_size_data ( -- 사이즈유형
  sino -- 사이즈번호
  );

-- 스타일사이즈
ALTER TABLE p_style_size
  ADD CONSTRAINT FK_p_style_TO_p_style_size -- 스타일 -> 스타일사이즈
  FOREIGN KEY (
  stno -- 스타일번호
  )
  REFERENCES p_style ( -- 스타일
  stno -- 스타일번호
  );