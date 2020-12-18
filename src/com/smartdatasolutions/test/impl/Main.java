package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends MemberFileConverter {



	private MemberImporterImpl memberImporter;
	private MemberExporterImpl memberExporter;

	public Main(MemberImporterImpl memberImporter, MemberExporterImpl memberExporter) {
		this.memberImporter = memberImporter;
		this.memberExporter = memberExporter;
	}

	@Override
	protected MemberExporter getMemberExporter( ) {

		return memberExporter;
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		return memberImporter;
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {
		return membersFromFile.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(Member::getId))), ArrayList::new));

	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		return validMembers.stream().collect(Collectors.groupingBy(Member::getState));

	}

	public static void main( String[] args ) throws Exception {
		new Main(new MemberImporterImpl(), new MemberExporterImpl()).convert(new File("../SDS_Entry_v2/Members.txt"), "./output/", "outputFile.csv");

	}

}
