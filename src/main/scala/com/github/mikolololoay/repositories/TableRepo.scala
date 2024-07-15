package com.github.mikolololoay.repositories


import zio.ZIO
import java.sql.SQLException
import izumi.reflect.Tag
import io.getquill.*
import io.getquill.SchemaMeta


trait TableRepo[A]:
    inline val tableName: String
    inline given schema: SchemaMeta[A] = schemaMeta[A](tableName)

    def getAll: ZIO[Any, SQLException, List[A]]
    def add(record: A): ZIO[Any, SQLException, Long]
    def add(newRecords: List[A]): ZIO[Any, SQLException, List[Long]]
    def delete(id: String): ZIO[Any, SQLException, Long]
    def truncate(): ZIO[Any, SQLException, Long]


object TableRepo:
    def getAll[A: Tag]: ZIO[TableRepo[A], SQLException, List[A]] =
        ZIO.serviceWithZIO[TableRepo[A]](_.getAll)
    
    def add[A: Tag](record: A): ZIO[TableRepo[A], SQLException, Long] =
        ZIO.serviceWithZIO[TableRepo[A]](_.add(record))
    
    def add[A: Tag](newRecords: List[A]): ZIO[TableRepo[A], SQLException, List[Long]] =
        ZIO.serviceWithZIO[TableRepo[A]](_.add(newRecords))

    def delete[A: Tag](id: String): ZIO[TableRepo[A], SQLException, Long] =
        ZIO.serviceWithZIO[TableRepo[A]](_.delete(id))

    def truncate[A: Tag](): ZIO[TableRepo[A], SQLException, Long] =
        ZIO.serviceWithZIO[TableRepo[A]](_.truncate())