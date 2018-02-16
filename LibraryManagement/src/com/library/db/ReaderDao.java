package com.library.db;

import java.util.List;

import com.library.bean.Reader;

public interface ReaderDao {
	
	public Reader get(int readerId);
	public List<Reader> get();
	public void create(Reader reader);
	public String update(Reader reader);
	public void delete(int readerId);

}
