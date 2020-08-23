package com.bb.hanja;

public class HanjaUtil {
/*
	(참고 1) 유니코드 범위
	2E80 2EFF CJK Radicals Supplement 한중일 부수 보충 
	31C0 31EF CJK Strokes 한중일 한자 획 
	3200 32FF Enclosed CJK Letters and Months 한중일 괄호 문자 
	3400 4DBF CJK Unified Ideographs Extension A 한중일 통합 한자 확장-A 
	4E00 9FBF CJK Unified Ideographs 한중일 통합 한자 
	F900 FAFF CJK Compatibility Ideographs 한중일 호환용 한자 
	
	(참고 2)
	원래는 [\u20000-\u2a6df\u2f800-\u2fa1f] 까지 포함되어 있었는데 
	역슬래시u + 5자리 유니코드는 일반적이지도 않고, (보통 역슬래시u + 4자리)
	숫자랑 일반 알파벳이 섞여있어서, 5자리 유니코드는 뺐음.
	
	20021 : !
	20022 : "
	20023 : #
	20024 : $
	20025 : %
	20026 : &
	20027 : '
	20028 : (
	20029 : )
	20030 : 0
	20031 : 1
	20032 : 2
	20033 : 3
	20034 : 4
	20035 : 5
	20036 : 6
	20037 : 7
	20038 : 8
	20039 : 9
	20040 : @
	20041 : A
	20042 : B
	20043 : C
	20044 : D
	20045 : E
	20046 : F
	20047 : G
	20048 : H
	20049 : I
	20050 : P
	20051 : Q
	20052 : R
	20053 : S
	20054 : T
	20055 : U
	20056 : V
	20057 : W
	20058 : X
	20059 : Y
	20060 : `
	20061 : a
	20062 : b
	20063 : c
	20064 : d
	20065 : e
	20066 : f
	20067 : g
	20068 : h
	20069 : i
	20070 : p
	20071 : q
	20072 : r
	20073 : s
	20074 : t
	20075 : u
	20076 : v
	20077 : w
	20078 : x
	20079 : y
*/
	
	public static String getHanjaOnly(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		
		// 원래는 [\u20000-\u2a6df\u2f800-\u2fa1f] 까지 포함되어 있었는데 
		// 역슬래시u + 5자리 유니코드는 일반적이지도 않고, (보통 역슬래시u + 4자리)
		// 숫자랑 일반 알파벳이 섞여있어서, 5자리 유니코드는 뺐음.
		String regEx = "[\u2e80-\u2eff\u31c0-\u31ef\u3200-\u32ff\u3400-\u4dbf\u4e00-\u9fbf\uf900-\ufaff]";
		
		StringBuffer buff = new StringBuffer();
		
		String oneMark = "";
		int len = str.length();
		for (int i=0; i<len; i++) {
			oneMark = str.substring(i, i+1);
			if (oneMark.matches(regEx)) {
				buff.append(oneMark);
			}
		}
		
		return buff.toString();
	}
}