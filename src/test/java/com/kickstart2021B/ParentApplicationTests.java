package com.kickstart2021B;


import org.junit.Test;

public class ParentApplicationTests {

	@Test
	public void contextLoads() {
		assert true;
	}

	@Test
	public void readFileA() {
		ParentApplication.main(new String[]{"a.txt"});
		// output a_out.txt
	}

	@Test
	public void readFileB() {
		ParentApplication.main(new String[]{"b.txt"});
		// output a_out.txt

	}

	@Test
	public void readFileC() {
		ParentApplication.main(new String[]{"c.txt"});
		// output a_out.txt

	}

	@Test
	public void readFileD() {
		ParentApplication.main(new String[]{"d.txt"});
		// output a_out.txt

	}

	@Test
	public void readFileE() {
		ParentApplication.main(new String[]{"e.txt"});
		// output a_out.txt

	}

	@Test
	public void readFileF() {
		ParentApplication.main(new String[]{"f.txt"});
		// output a_out.txt

	}


}
