package com.baiyan.common.base.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author baiyan
 * @date 2021/11/29
 */
@Slf4j
public class CmdUtil {

    /**
     * 执行命令
     * @param cmd
     * @return
     */
    public static Boolean executeCmd(String cmd){
        String command = cmd.replaceAll("//", "/");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",command);
        try {
            Process process = processBuilder.start();
            int status = process.waitFor();
            log.info("{}:{}",command,status);
            return status == 0;
        } catch (Exception e) {
            log.error(e.getMessage() );
            return false;
        }
    }


    /**
     * 执行命令
     *
     * 默认情况下在当前环境执行，即当前如果为docker部署则需要在docker中ssh拨至宿主机执行
     *
     * @param cmd
     * @return
     */
    public static String executeLinuxCmd(String cmd){
        String[] cmdArray = {"sh", "-c", cmd};
        return executeShell(true, cmdArray);
    }

    /**
     * 执行命令
     *
     * @param withLine: 多行时是否换行
     * @param cmd: 执行指令 第一个参数是可执行命令程序，其他的是命令行执行是需要的参数
     * @return: java.lang.String
     **/
    public static String executeShell(boolean withLine, String... cmd){
        return executeShell(null, withLine, cmd);
    }

    /**
     * 执行shell
     *
     * @param filePath: 默认值是当前进程的当前工作目录，通常根据系统属性 user.dir 来命名
     * @param withLine:
     * @param cmd:
     * @return: java.lang.String
     **/
    public static String executeShell(File filePath, boolean withLine, String... cmd){
        StringBuilder cmdResult = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.directory(filePath);
            Process process = processBuilder.redirectErrorStream(true).start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                String line;

                while ((line = reader.readLine()) != null){
                    cmdResult.append(line);
                    if (withLine){
                        cmdResult.append(System.lineSeparator());
                    }
                }
                log.debug(cmdResult.toString());
            }
            int existCode = process.waitFor();
            log.debug("execute cmd {} success, exist code {}", Arrays.toString(cmd), existCode);
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        if (withLine){
            return cmdResult.substring(0, cmdResult.length()).trim();
        }
        return cmdResult.toString().trim();
    }

}
