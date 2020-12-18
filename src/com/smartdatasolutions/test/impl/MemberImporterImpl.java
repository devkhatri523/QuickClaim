package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {

	@Override
	public List< Member > importMembers( File inputFile ) throws Exception {
		List<Member> memInfo = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

			String line = br.readLine();
			while (line != null) {
				String[] memberInfo = line.split("\\s{2,50}");


                /* if condition is applied to avoid arrayIndexOutOfBound
				 as there is concatination on members.txt file on column state and zip on some of the row
                 */
				if(memberInfo.length == 7) {
					Member member = new Member();
					member.setId(memberInfo[0]);
					member.setLastName(memberInfo[1]);
					member.setFirstName(memberInfo[2]);
					member.setAddress(memberInfo[3]);
					member.setCity(memberInfo[4]);
					member.setState(memberInfo[5]);
					member.setZip(memberInfo[6]);
					memInfo.add(member);

				}
				line = br.readLine();
			}

		}

		return memInfo;
	}
	
	

}
