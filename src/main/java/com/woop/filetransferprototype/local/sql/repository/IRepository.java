
package com.woop.filetransferprototype.local.sql.repository;

import com.woop.filetransferprototype.local.sql.connection.SQLiteJDBCDriverConnection;
import java.util.List;

/**
 *
 * @author NoID
 */
public interface IRepository<T> {
    T getById(int id);
    boolean save(T entity);
    boolean remove(int id);
}
