package com.krishagni.catissueplus.core.de.services;

import com.krishagni.catissueplus.core.de.events.AddFormContextsEvent;
import com.krishagni.catissueplus.core.de.events.AllFormsSummaryEvent;
import com.krishagni.catissueplus.core.de.events.EntityFormRecordsEvent;
import com.krishagni.catissueplus.core.de.events.EntityFormsEvent;
import com.krishagni.catissueplus.core.de.events.FileDetailEvent;
import com.krishagni.catissueplus.core.de.events.FileUploadedEvent;
import com.krishagni.catissueplus.core.de.events.FormContextsAddedEvent;
import com.krishagni.catissueplus.core.de.events.FormContextsEvent;
import com.krishagni.catissueplus.core.de.events.FormDataEvent;
import com.krishagni.catissueplus.core.de.events.FormDefinitionEvent;
import com.krishagni.catissueplus.core.de.events.FormFieldsEvent;
import com.krishagni.catissueplus.core.de.events.ReqAllFormsSummaryEvent;
import com.krishagni.catissueplus.core.de.events.ReqEntityFormRecordsEvent;
import com.krishagni.catissueplus.core.de.events.ReqEntityFormsEvent;
import com.krishagni.catissueplus.core.de.events.ReqFileDetailEvent;
import com.krishagni.catissueplus.core.de.events.ReqFormContextsEvent;
import com.krishagni.catissueplus.core.de.events.ReqFormDataEvent;
import com.krishagni.catissueplus.core.de.events.ReqFormDefinitionEvent;
import com.krishagni.catissueplus.core.de.events.ReqFormFieldsEvent;
import com.krishagni.catissueplus.core.de.events.SaveFormDataEvent;
import com.krishagni.catissueplus.core.de.events.UploadFileEvent;

public interface FormService {
	public AllFormsSummaryEvent getForms(ReqAllFormsSummaryEvent req);
	
	public FormDefinitionEvent getFormDefinition(ReqFormDefinitionEvent req);
	
	public FormFieldsEvent getFormFields(ReqFormFieldsEvent req);
	
	public FormContextsEvent getFormContexts(ReqFormContextsEvent req);
	
	public FormContextsAddedEvent addFormContexts(AddFormContextsEvent req);
	
	public EntityFormsEvent getEntityForms(ReqEntityFormsEvent req);
	
	public EntityFormRecordsEvent getEntityFormRecords(ReqEntityFormRecordsEvent req);
	
	public FormDataEvent getFormData(ReqFormDataEvent req);
	
	public FormDataEvent saveFormData(SaveFormDataEvent req);

	public FileDetailEvent getFileDetail(ReqFileDetailEvent req);

	public FileUploadedEvent uploadFile(UploadFileEvent req); 
	
}
