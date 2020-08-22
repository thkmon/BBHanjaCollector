package com.bb.hanja;

import java.io.File;
import java.util.ArrayList;

public class BBHanjaCollector {

	public static void main(String[] args) {
		
		try {
			long beginTime = System.currentTimeMillis();
			
			System.out.println("BBHanjaCollector_" + CConst.version);
			System.out.println("");
			
			System.out.println("사용방법");
			System.out.println("----------");
			System.out.println("input.txt 파일 안에 한글/한자가 섞여있는 내용을 붙여넣기합니다.");
			System.out.println("이후 본 프로그램을 수행하면 output.txt 파일이 생성됩니다.");
			System.out.println("----------");
			System.out.println("");
			
			// Start reading file contents of input.txt
			File inputFile = new File("input.txt");
			System.out.println("파일 읽기 시작 (" + inputFile.getAbsolutePath() + ")");
			if (!inputFile.exists()) {
				// The file of input.txt does not exists
				System.out.println("input.txt 파일이 존재하지 않습니다.");
				return;
			}
			
			StringList fileContent = FileUtil.readFile(inputFile, "UTF-8");
			
			if (fileContent == null || fileContent.size() == 0) {
				// The file of input.txt contents is empty
				System.out.println("input.txt 파일 내용이 비어있습니다.");
				return;
			}
			
			System.out.println("파일 읽기 완료");
			System.out.println("");
		
			StringBuilder build = new StringBuilder();
			String line = "";
			int lineCount = fileContent.size();
			for (int i=0; i<lineCount; i++) {
				line = fileContent.get(i);
				if (line == null || line.length() == 0) {
					continue;
				}
				
				line = HanjaUtil.getHanjaOnly(line);
				build.append(line);
			}
			
			// Start removing duplication
			System.out.println("중복제거 시작");
			String distinctText = StringUtil.getDuplRemovedText(build.toString());
			System.out.println("중복제거 완료");
			System.out.println("");
			
			// Start sorting
			System.out.println("정렬 시작");
			String sortedText = StringUtil.getSortedText(distinctText);
			System.out.println("정렬 완료");
			System.out.println("");
			
			int charCount = sortedText.length();
			if (charCount % 2 == 1) {
				charCount++;
			}
			
			int hanjaCount = charCount / 2;
			System.out.println("추출한 한자 개수 : " + hanjaCount + "개");
			System.out.println("");
			
			// Start writing file contents of output.txt
			File outputFile = new File("output.txt");
			System.out.println("파일 읽기 시작 (" + outputFile.getAbsolutePath() + ")");
			FileUtil.writeFile(outputFile.getAbsolutePath(), sortedText, "UTF-8", false);
			System.out.println("파일 쓰기 완료");
			System.out.println("");
			
			// End the program normally
			System.out.println("프로그램을 정상 종료합니다.");
			
			// 텍스트 파일 열기
			ProcessBuilder builder = null;
		    try {
		        ArrayList<String> commandList = new ArrayList();
		        commandList.add("notepad.exe");
		        commandList.add(outputFile.getAbsolutePath());
		            
		        builder = new ProcessBuilder(commandList);
		        builder.start();
		        
		    } catch (Exception e) {
		    	// e.printStackTrace();
		    	System.out.println("output.txt 파일을 열어보세요. (" + outputFile.getAbsolutePath() + ")");
		    	
		    } finally {
		    	builder = null;
		    }
			
		    // 수행시간 계산
		    long endTime = System.currentTimeMillis();
		    
		    long ms = endTime - beginTime;
		    float sec = (float) ms / 1000;
		    float min = (float) sec / 60;
		    
		    if (min > 1) {
		        System.out.println("수행시간: " + min + "분");
		        
		    } else {
		        System.out.println("수행시간: " + sec + "초");
		    }
		    
		} catch (Exception e) {
			// Terminating the program because of an error
			System.out.println("오류로 인해 프로그램을 종료합니다.");
			e.printStackTrace();
			
		} finally {
			try {
				// 글자를 읽을 수 있도록 1분 대기 후 종료
				Thread.sleep(1000 * 60);
			} catch (Exception e) {
				// ignore
			}
		}
	}
}