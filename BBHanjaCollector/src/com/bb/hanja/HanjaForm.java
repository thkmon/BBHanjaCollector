package com.bb.hanja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HanjaForm {
	
	public static JTextField targetFolderText = null;
	public static JCheckBox javaToClassCheckBox = null;
	
	private int top = 0;
	
	private void plusTop(int num) {
		top = top + (25 * num);
	}
	private void plusTopLittle(int num) {
		top = top + (10 * num);
	}
	
	public static JTextArea textArea = null;
	public static JTextField destDirText = null;
	public static JTextField forbiddenFileText = null;
	
	
	public HanjaForm() {
		
		String title = "한자수집기";
		if (CConst.version != null && CConst.version.length() > 0) {
			title = title + "_" + CConst.version;
		}
		
		BasicForm bForm = new BasicForm(CConst.winWidth, CConst.winHeight, title);
		
		bForm.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("사용자 명령으로 종료합니다.");
				System.exit(0);
			}
		});
		
		int width = 560;
		int left = 10;
		
		// 대상폴더
		plusTop(1);
		JButton button = bForm.addButton(10, top, 130, 24, "한자만 수집");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("한자만 수집 실행");
				String oldStr = textArea.getText();
				String newStr = HanjaUtil.getHanjaOnly(oldStr);
				textArea.setText(newStr);
				
				printLine();
				textArea.setFocusable(true);
			}
		});
		
		JButton button2 = bForm.addButton(150, top, 130, 24, "정렬");
		button2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("정렬 실행");
				String oldStr = textArea.getText();
				String newStr = StringUtil.getSortedText(oldStr);
				textArea.setText(newStr);
				
				printLine();
				textArea.setFocusable(true);
			}
		});
		
		JButton button3 = bForm.addButton(290, top, 130, 24, "중복제거");
		button3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("중복제거 실행");
				String oldStr = textArea.getText();
				String newStr = StringUtil.getDuplRemovedText(oldStr);
				textArea.setText(newStr);
				
				printLine();
				textArea.setFocusable(true);
			}
		});
		
		plusTop(1);
		plusTop(1);
		textArea = bForm.addTextArea(left, top, width, 340);
		
		plusTop(7);
		plusTopLittle(1);
		plusTopLittle(1);
		
		bForm.open();
	}
	
	public static void printLine() {
//		try {
//			int currentLineNumber = textArea.getCaretLineNumber() + 1;
//			String str = "(" + currentLineNumber + "/" + textArea.getLineCount() + ")"; 
//			label.setText(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
