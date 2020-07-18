package c0320h1.system.support.converter;

import c0320h1.system.http.UploadedFile;
import c0320h1.system.filesystem.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MultipartFileToConcernsFile implements Converter<MultipartFile, UploadedFile> {
	@Autowired
	private Storage storage;

	@Override
	public UploadedFile convert(MultipartFile source) {
		return new UploadedFile(source, storage);
	}
}
