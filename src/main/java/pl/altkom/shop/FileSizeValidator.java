package pl.altkom.shop;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private int maxSize;

	@Override
	public void initialize(FileSize arg0) {
		this.maxSize = arg0.size() * 1024;

	}

	@Override
	public boolean isValid(MultipartFile arg0, ConstraintValidatorContext arg1) {
		if (arg0 == null) {
			return true;
		}
		return arg0.getSize() < maxSize;
	}

}
