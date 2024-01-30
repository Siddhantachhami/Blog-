package com.siddhant.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="category of title must be 4 characters")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message="category of desc must be 10 characters")
	private String categoryDescription;

}
