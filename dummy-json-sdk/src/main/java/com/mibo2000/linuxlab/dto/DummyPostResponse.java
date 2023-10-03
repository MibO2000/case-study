package com.mibo2000.linuxlab.dto;

import com.mibo2000.linuxlab.model.dto.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : MibO2000
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DummyPostResponse extends DummyBasicResponse {
    List<Post> posts;
}
