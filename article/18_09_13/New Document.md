#用于配置文件的加密解密

1.对于中文来说可以先转ascii码

在CMD命令行中进行转换：

	native2ascii
	/**输入汉字**/
	

2.利用Java代码实现加密解密：
    
    public class EncryptDecrypt {
    	/**
    	 * 加密
    	 * 
    	 * @param pass
    	 * @return
    	 */
    	public static String Encrypt(String pass) {
    		int len = pass.length();
    		char enPass[] = new char[len * 2];
    		int chk[] = { 104, 115, 109, 97, 122, 104, 106, 104 };
    		if (pass == null || pass.equals(""))
    			return "";
    		int i = 0;
    		for (int j = 0; i < len; j++) {
    			if (j == 8) {
    				j = 0;
    			}
    			int temp = pass.charAt(i) ^ chk[j];
    			String C = Integer.toString(temp, 16);
    			if (C.length() > 1) {
    				enPass[i * 2] = C.charAt(0);
    				enPass[i * 2 + 1] = C.charAt(1);
    			} else {
    				enPass[i * 2] = '0';
    				enPass[i * 2 + 1] = C.charAt(0);
    			}
    			i++;
    		}
    
    		return new String(enPass);
    	}
    
    	/**
    	 * 解密
    	 * 
    	 * @param enPass
    	 * @return
    	 */
    	public static String unEncrypt(String enPass) {
    		int len = enPass.length();
    		if (len % 2 != 0) {
    			return "";
    		}
    		if (enPass == null || enPass.equals(""))
    			return "";
    		char pass[] = new char[len / 2];
    		char C[] = new char[2];
    		int chk[] = { 104, 115, 109, 97, 122, 104, 106, 104 };
    		int i = 0;
    		for (int j = 0; i < len / 2; j++) {
    			if (j == 8) {
    				j = 0;
    			}
    			C[0] = enPass.charAt(i * 2);
    			C[1] = enPass.charAt(i * 2 + 1);
    			int temp = Integer.parseInt(String.valueOf(C), 16);
    			pass[i] = (char) (temp ^ chk[j]);
    			i++;
    		}
    
    		return new String(pass);
    	}
    
    	public static void main(String args[]) {
    		System.out.println("\u8BF7\u8F93\u5165\u6570\u636E\u5E93\u5BC6\u7801(\u56DE\u8F66\u786E\u8BA4):");
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		try {
    			String pwd = br.readLine();
    			System.out.println("================\u56DE\u8F66\u79BB\u5F00");
    			System.out.println("\u539F\u5BC6\u7801\uFF1A" + pwd);
    			System.out.println("\u52A0\u5BC6\u5BC6\u7801\uFF1A" + Encrypt(pwd));
    			System.out.println("\u89e3\u5bc6\u52A0\u5BC6\u5BC6\u7801\uFF1A" + unEncrypt(Encrypt(pwd)));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }