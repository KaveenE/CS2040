package oneDay_assignments;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fast_input_classes.FastReader;

public class Conformity {

	public static final int NUM_COURSES = 5;
	
	public static void main(String[] args) throws IOException 
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		FastReader scanner = new FastReader();
		
		Set<Integer> someCourse;
		Map<Set<? super Integer>, Integer> courseFrequency = new HashMap<>();
		
		int student = scanner.nextInt();
		
		int i,j,numStudentsForPopularCourse = 0;
		for( i = 1; i <= student; i++)
		{
			someCourse = new HashSet<>();
			for(j = 1; j<= NUM_COURSES; j++)
			{
				someCourse.add(scanner.nextInt());
			}
			
			courseFrequency.put(someCourse, courseFrequency.getOrDefault(someCourse, 0) + 1);
			numStudentsForPopularCourse = Math.max(courseFrequency.getOrDefault(someCourse, 0), numStudentsForPopularCourse);
		}
		
		//Similar to how we iterate thru frequency counter in C to get ties, we iterate thru the Map
		int studentsWithPopularCourse_withTie = 0;
		for(int numStudents : courseFrequency.values())
		{
			if(numStudents == numStudentsForPopularCourse)
			{
				studentsWithPopularCourse_withTie += numStudentsForPopularCourse;
			}
		}
		
		out.write(studentsWithPopularCourse_withTie+"");
		out.flush();
		
	}

}
