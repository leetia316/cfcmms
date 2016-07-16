package com.jfok.cfcmms.logic;

import java.io.File;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.log._Systembackup;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.service.SystemInfoService;
import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.FileOperate;
import com.jfok.cfcmms.util.TypeChange;

@Service
public class _SystembackupLogic extends BaseOperateLogic<_Systembackup> {

	private static final String us_ERROR_BACKUP = "数据备份失败,请与服务单位联系";
	private static final String us_ERROR_BACKUPZIP = "数据备份文件压缩失败,请于服务单位联系";

	@Resource
	private SystemBaseDAO systemBaseDAO;

	private Timer timer;
	private MyTask mytask;

	public _SystembackupLogic() {
		// System.out.println("时间触发器开始");
		timer = new Timer();
		mytask = new MyTask();
		// 多少秒手执行，每隔一小时检查一次
		Calendar beginDate = Calendar.getInstance();
		// 时间设为第二天
		beginDate.setTime(CommonFunction.getNextDate(new Date()));
		// 第一次触发时间为 凌晨 0：00 -- 2：59
		beginDate.set(Calendar.HOUR_OF_DAY, new Random().nextInt(3));
		beginDate.set(Calendar.MINUTE, new Random().nextInt(60));

		// 测试用，当前时间，
		// beginDate.setTime(new Date());
		// beginDate.set(Calendar.HOUR_OF_DAY, 15);
		// beginDate.set(Calendar.MINUTE,33);

		// System.out.println("每天这个时间开始备份：" + beginDate.getTime());
		timer.schedule(mytask, beginDate.getTime(), 24 * 60 * 60 * 1000);

	}

	@Override
	public boolean beforeInsert(_Systembackup inserted, Map<String, String> errorMessage,
			HttpServletRequest request) {
		FileOperate.newFolder(SystemInfoService.getSystemsetAddition().firstBackupFileDir());
		String bname = inserted.getTf_backupfilename().replaceFirst("\\.dat", "");
		ExecBackupProc(bname);
		inserted.setTf_result("备份成功!");
		File f = new File(bname);
		if (!f.exists()) {
			inserted.setTf_result(us_ERROR_BACKUP);
		} else {
			try {
				String firstfile = inserted.getTf_backupfilename();
				CommonFunction.Zipfile(bname, firstfile);
				// 将数据备份到第二路径当中，可以是U盘，或者网络上的其他位置
				String[] otherdirs = SystemInfoService.getSystemsetAddition().allBackupFileDirs();
				if (otherdirs != null)
					for (String otherdir : otherdirs) {
						try {
							String otherfile = otherdir
									+ SystemInfoService.getSysteminfo().getTf_backupfilename()
									+ TypeChange.DateToString(new Date()) + ".dat";
							FileOperate.newFolder(otherdir);
							FileOperate.copyFile(firstfile, otherfile);
							inserted.setTf_otherfiles((inserted.getTf_otherfiles() == null ? "" : inserted
									.getTf_otherfiles()) + otherfile + "*");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				FileOperate.delFile(bname);
			} catch (Exception e) {
				inserted.setTf_result(us_ERROR_BACKUPZIP);
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean beforeDelete(_Systembackup deleted, List<String> errorMessage,
			HttpServletRequest request) {
		String file = deleted.getTf_backupfilename();
		if (new File(file).exists())
			FileOperate.delFile(file);
		if (deleted.getTf_otherfiles() != null) {
			String bfiles[] = deleted.getTf_otherfiles().split("\\*");
			for (String bfile : bfiles)
				if (new File(bfile).exists())
					FileOperate.delFile(bfile);
		}
		return super.beforeDelete(deleted, errorMessage, request);
	}

	@Override
	public Map<String, Object> getNewDefultValue(HttpServletRequest request,
			GridFilterData gridFilterData) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("tf_backupdate", TypeChange.DateToString(new Date()));
		result.put("tf_ipaddress", CommonFunction.getIpAddr(request));
		result.put("tf_userName", SessionManage.getInstance().getUserSession(request.getSession())
				.getUserName());
		result.put("tf_backupfilename", filename());
		return result;
	}

	private String filename() {
		return SystemInfoService.getSystemsetAddition().firstBackupFileDir()
				+ SystemInfoService.getSysteminfo().getTf_backupfilename()
				+ TypeChange.DateToString(new Date()) + ".dat";
	}

	/**
	 * 执行备份存贮过程,将数据库文件备份到 filename 中
	 * 
	 * @param filename
	 */

	public void ExecBackupProc(String filename) {
//		Connection con = systemBaseDAO.getSessionFactory().openSession().connection();
//		try {
//			con.setAutoCommit(true);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		String procedure = "{call dbo.BackupDatabase(?) }";
//		try {
//			CallableStatement cstmt = con.prepareCall(procedure);
//			cstmt.setString(1, filename);
//			cstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}

	/**
	 * 自动执行backup
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean fu_autoExecBackup() {

		// 开始新的备份
		// log.error("自动备份开始");
		_Systembackup systembackup = new _Systembackup();
		systembackup.setTf_inputdate(new Date());
		systembackup.setTf_backupdate(new Date());
		systembackup.setTf_ipaddress("127.0.0.1");
		systembackup.setTf_userName("自动备份");
		systembackup.setTf_inputmen("系统");
		systembackup.setTf_remark("自动备份");
		systembackup.setTf_backupfilename(filename());
		beforeInsert(systembackup, null, null);
		// log.error( systembackup.getTf_backupfilename() + "  自动备份结束");

		Session session = systemBaseDAO.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.save(systembackup);
		// 删除一个月前的备份文件
		try {
			Date now = new Date();
			List<_Systembackup> allbackup = (List<_Systembackup>) systemBaseDAO.findAll(_Systembackup.class);
			for (_Systembackup b : allbackup) {
				long d = (now.getTime() - b.getTf_backupdate().getTime()) / 1000 / 60 / 60 / 24;
				if (d > 30) {
					String file = b.getTf_backupfilename();
					if (new File(file).exists())
						FileOperate.delFile(file);
					if (b.getTf_otherfiles() != null) {
						String bfiles[] = b.getTf_otherfiles().split("\\*");
						for (String bfile : bfiles)
							if (new File(bfile).exists())
								FileOperate.delFile(bfile);
					}
					session.delete(b);
				}
			}
		} catch (Exception e) {
		}

		tx.commit();
		session.close();

		return true;
	}

	class MyTask extends java.util.TimerTask {
		@Override
		public void run() {
			// //系统每天清晨自动备份，同时删除掉3个月前的备份
			try {
				fu_autoExecBackup();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}