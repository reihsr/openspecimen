/**
 * <p>Title: ReceivedEventParameters Class</p>
 * <p>Description: Attributes associated with the received event of a specimen. </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 */

package edu.wustl.catissuecore.domain;

import edu.wustl.catissuecore.actionForm.ReceivedEventParametersForm;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.actionForm.IValueObject;
import edu.wustl.common.util.logger.Logger;

/**
 * Attributes associated with the received event of a specimen.
 * @hibernate.joined-subclass table="CATISSUE_RECEIVED_EVENT_PARAM"
 * @hibernate.joined-subclass-key column="IDENTIFIER" 
 */
public class ReceivedEventParameters extends SpecimenEventParameters
		implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	/**
	 * Grossly evaluated quality of the received specimen.
	 */
	protected String receivedQuality;

	/**
	 * Returns the receivedQuality of the specimen.
	 * @hibernate.property name="receivedQuality" type="string" column="RECEIVED_QUALITY" length=50"
	 * @return receivedQuality of the specimen.
	 */
	public String getReceivedQuality()
	{
		return receivedQuality;
	}

	/**
	 * Sets the receivedQuality of the SPECIMEN.
	 * @param receivedQuality receivedQuality of the SPECIMEN.
	 */
	public void setReceivedQuality(String receivedQuality)
	{
		this.receivedQuality = receivedQuality;
	}
	
	public ReceivedEventParameters()
	{
		
	}
//	Parameterized constructor
	public ReceivedEventParameters(AbstractActionForm abstractForm)
	{
		setAllValues((IValueObject)abstractForm);
	}
	
	/**
     * This function Copies the data from an ReceivedEventParametersForm object to a ReceivedEventParameters object.
     * @param receivedEventParametersForm An ReceivedEventParametersForm object containing the information about the ReceivedEventParameters.  
     */
    public void setAllValues(IValueObject abstractForm)
    {
        try
        {
            ReceivedEventParametersForm form = (ReceivedEventParametersForm) abstractForm;
            this.receivedQuality = form.getReceivedQuality();  
            super.setAllValues(form);
//        	//call to event parameters setallvalue method
//        	super.setAllValues(abstractForm);
          
            Logger.out.debug("receivedQuality: "+receivedQuality);
        }
        catch (Exception excp)
        {
            Logger.out.error(excp.getMessage());
        }
    }
    public ReceivedEventParameters(ReceivedEventParameters obj)
    {
    	this.receivedQuality = obj.receivedQuality;
    	this.comment = obj.comment;
    }
    
}