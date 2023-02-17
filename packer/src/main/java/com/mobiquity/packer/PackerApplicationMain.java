package com.mobiquity.packer;

import com.mobiquity.exception.APIException;

public class PackerApplicationMain {
    public static void main(String[] args) throws APIException {
        Packer.pack("C:\\Users\\user\\IdeaProjects\\Mobiquity\\packer\\src\\test\\resources\\example_input");
    }
}
