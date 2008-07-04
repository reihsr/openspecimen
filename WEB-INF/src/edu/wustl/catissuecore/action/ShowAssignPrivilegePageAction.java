package edu.wustl.catissuecore.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import edu.wustl.catissuecore.actionForm.CollectionProtocolForm;
import edu.wustl.catissuecore.bean.CollectionProtocolBean;
import edu.wustl.catissuecore.bizlogic.AssignPrivilegePageBizLogic;
import edu.wustl.catissuecore.multiRepository.bean.SiteUserRolePrivilegeBean;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.listener.CatissueCoreServletContextListener;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.dao.AbstractDAO;
import edu.wustl.common.dao.DAOFactory;
import edu.wustl.common.exception.BizLogicException;
import edu.wustl.common.factory.AbstractBizLogicFactory;
import edu.wustl.common.security.exceptions.SMException;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.logger.Logger;
import gov.nih.nci.security.exceptions.CSException;

/**
 * Sets data for site,user,action and role on page loading and handles the ajax requests.
 * @author vipin_bansal
 */
public class ShowAssignPrivilegePageAction extends BaseAction {
	/**
	 * Overrides the execute method of Action class. Initializes the various
	 * fields in AssignPrivilege.jsp Page.
	 */
	private static org.apache.log4j.Logger logger =Logger.getLogger(CatissueCoreServletContextListener.class);  
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws ServletException { 
		ActionForward findForward = null;
		final CollectionProtocolForm  cpForm = (CollectionProtocolForm)form;
		if(cpForm!=null)
		{
			CollectionProtocolBean collectionProtocolBean = (CollectionProtocolBean) request.getSession().getAttribute(Constants.COLLECTION_PROTOCOL_SESSION_BEAN);
			collectionProtocolBean.setSiteIds(cpForm.getSiteIds());
		}
		final String operation = (String) request.getParameter(Constants.OPERATION);
		final String cpOperation = (String) request.getParameter("cpOperation");
		request.setAttribute("cpOperation", cpOperation);
		if (("AssignPrivilegePage".equals(cpOperation))) {
			findForward = onFirstTimeLoad(mapping, request);
		} else {
			try {
				setAJAXResponse(request, response, cpOperation);
			} catch (IOException e) {
				logger.error("IOException in  sending JSON response in ShowAssignPrivilegePageAction..."+e);
			}
		}
		return findForward;
	}
	/**
	 * Gives Bizlogic for AssignPrivilege
	 * @return AssignPrivilegePageBizLogic
	 */
	public AssignPrivilegePageBizLogic getAssignPrivilegePageBizLogic() {
		AssignPrivilegePageBizLogic apBizLogic = null;
		try {
			apBizLogic = (AssignPrivilegePageBizLogic) AbstractBizLogicFactory
			.getBizLogic(ApplicationProperties
					.getValue("app.bizLogicFactory"), "getBizLogic",
					Constants.ASSIGN_PRIVILEGE_FORM_ID);
		} catch (BizLogicException e) {
			logger.error("BizLogicException in getting Bizlogic for AssignPrivilegePageBizLogic in ShowAssignPrivilegePageAction..."+e);
		}
		return apBizLogic;  
	}
	/**
	 * Gets lists og sites,users,actions,and roles from database and sets in request.  
	 * @param mapping
	 * @param request
	 * @return ActionForward
	 */
	private ActionForward onFirstTimeLoad(ActionMapping mapping, HttpServletRequest request) {
		
			final AssignPrivilegePageBizLogic apBizLogic=getAssignPrivilegePageBizLogic();
			HttpSession session=request.getSession();
			Map<String, SiteUserRolePrivilegeBean> rowIdBeanMap = (Map<String, SiteUserRolePrivilegeBean>) session.getAttribute(Constants.ROW_ID_OBJECT_BEAN_MAP);
			
			try {
				if (session.getAttribute(Constants.ROW_ID_OBJECT_BEAN_MAP) != null) {
					List<String[]> list;
						list = apBizLogic.privilegeDataOnTabSwitch(rowIdBeanMap);
						request.setAttribute("listOnLoad", list);
				}
			
				final List<NameValueBean> siteList = apBizLogic.getSiteList(false);
				request.setAttribute(Constants.SITELIST, siteList);
				final List<NameValueBean> userList = apBizLogic.getUserList(false);
				request.setAttribute(Constants.USERLIST, userList);
				final List roleList = apBizLogic.getRoleList();
				request.setAttribute(Constants.ROLELIST, roleList);
				final List actionList = apBizLogic.getActionList(false);
				request.setAttribute(Constants.ACTIONLIST, actionList);
			} catch (BizLogicException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return mapping.findForward(Constants.SUCCESS);
	}
	
	/**
	 * Handles Ajax requests 
	 * @param request
	 * @param response
	 * @param operation
	 * @throws IOException 
	 * @throws IOException
	 */
	private void setAJAXResponse(HttpServletRequest request, HttpServletResponse response, String operation) throws IOException  {
		final HttpSession session = request.getSession();
		try {
			final AssignPrivilegePageBizLogic apBizLogic=getAssignPrivilegePageBizLogic();
			if (Constants.OPERATION_GET_USER_FORTHIS_SITES.equals(operation)) 
			{
				final String siteIds = (String) request.getParameter(Constants.SELECTED_SITE_IDS);
			    final List<Integer> listOfSiteIds = apBizLogic.getSiteData(siteIds);
			    final List<JSONObject> listOfUsers = apBizLogic.getUsersForThisSites(listOfSiteIds);
			    
				setResponse(response, listOfUsers);
				
			} else if (Constants.OPERATION_GET_ACTION_FORTHIS_ROLE.equals(operation)) {
				final String role = (String) request.getParameter(Constants.SELECTED_ROLE_IDS);
				final List<JSONObject> listOfAction = apBizLogic.getActionsForThisRole(role);
				
				setResponse(response, listOfAction);
				
			} else if (Constants.OPERATION_ADD_PRIVILEGE.equals(operation)) {
				
				final String siteIds = (String) request.getParameter(Constants.SELECTED_SITE_IDS);
				final String actionIds = (String) request.getParameter(Constants.SELECTED_ACTION_IDS);
				final String userIds = (String) request.getParameter(Constants.SELECTED_USER_IDS);
	 			final String roleId = (String) request.getParameter(Constants.SELECTED_ROLE_IDS);
	 			Map<String, SiteUserRolePrivilegeBean> rowIdBeanMap= new HashMap<String, SiteUserRolePrivilegeBean>();
	 			if (session.getAttribute(Constants.ROW_ID_OBJECT_BEAN_MAP) != null) {
	 				rowIdBeanMap = (Map<String, SiteUserRolePrivilegeBean>) session.getAttribute("rowIdObjectBeanMap");
	 			}	
	 			
				final List<JSONObject> listForUPSummary =apBizLogic.addPrivilege(rowIdBeanMap,userIds,siteIds,roleId,actionIds );
				session.setAttribute(Constants.ROW_ID_OBJECT_BEAN_MAP,	rowIdBeanMap);
				
				setResponse(response, listForUPSummary);
				
			} else if (Constants.OPERATION_DELETE_ROW.equals(operation)) {
				final String deletedRowsArray = (String) request.getParameter("deletedRowsArray");
				
				Map<String, SiteUserRolePrivilegeBean> rowIdBeanMap= new HashMap<String, SiteUserRolePrivilegeBean>();
				if (session.getAttribute(Constants.ROW_ID_OBJECT_BEAN_MAP) != null) {
					rowIdBeanMap = (Map<String, SiteUserRolePrivilegeBean>) session.getAttribute("rowIdObjectBeanMap");
				}
				rowIdBeanMap=apBizLogic.deletePrivilege(rowIdBeanMap,deletedRowsArray);
				session.setAttribute(Constants.ROW_ID_OBJECT_BEAN_MAP,	rowIdBeanMap);
				
				final List<JSONObject> nullList=null;
				setResponse(response, nullList);
				
			}
		} catch (JSONException e) {
			logger.error("JSONException in sending JSON response in ShowAssignPrivilegePageAction..."+e);
		} catch (BizLogicException exc){
			
		}
	}
	/**
	 * Sets  response to the Ajax requests.
	 * @param response
	 * @param arrayList
	 * @throws IOException
	 * @throws JSONException
	 */
	private void setResponse(HttpServletResponse response, List arrayList) throws IOException, JSONException {
		response.flushBuffer();
		response.getWriter().write(new JSONObject().put("locations", arrayList).toString());
	}
}
