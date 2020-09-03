-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATETABLEIFNOTEXISTS `undo_log` (
  `id` BIGINT (20) NOTNULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` BIGINT (20) NOTNULLCOMMENT 'branch transaction id',
  `xid` VARCHAR (100) NOTNULLCOMMENT 'global transaction id',
  `context` VARCHAR (128) NOTNULLCOMMENT 'undo_log context,such as serialization',
  `rollback_info` LONGBLOB NOTNULLCOMMENT 'rollback info',
  `log_status` INT (11) NOTNULLCOMMENT '0:normal status,1:defense status',
  `log_created` DATETIME NOTNULLCOMMENT 'create datetime',
  `log_modified` DATETIME NOTNULLCOMMENT 'modify datetime',
  PRIMARY KEY (`id`),
  UNIQUEKEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDBAUTO_INCREMENT = 1 DEFAULTCHARSET = utf8 COMMENT = 'AT transaction mode undo table' ;