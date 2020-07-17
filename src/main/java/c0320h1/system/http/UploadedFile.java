package c0320h1.system.http;

import c0320h1.system.filesystem.Storage;
import org.springframework.web.multipart.MultipartFile;

public class UploadedFile {
	private MultipartFile file;

	private Storage storage;

	public UploadedFile(){
	}

	public UploadedFile(MultipartFile multipartFile, Storage storage){
		this.storage = storage;
		this.file = multipartFile;
	}

	public MultipartFile getFile() {
		return file;
	}

	public UploadedFile disk(String disk){
		storage.disk(disk);
		return this;
	}

	public void store(String path){
		storage.putFile(path, file);
	}

	public void store(String path, String newName){
		storage.putFile(path, file, newName);
	}
}
