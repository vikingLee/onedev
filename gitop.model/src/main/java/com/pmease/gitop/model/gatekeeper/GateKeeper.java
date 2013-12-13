package com.pmease.gitop.model.gatekeeper;

import java.io.Serializable;

import com.pmease.commons.editable.annotation.Editable;
import com.pmease.commons.util.trimmable.Trimmable;
import com.pmease.gitop.model.PullRequest;
import com.pmease.gitop.model.gatekeeper.checkresult.CheckResult;

@Editable
public interface GateKeeper extends Trimmable, Serializable {
	
	public static final String CATEGORY_COMPOSITE = "Composite Conditions";

	public static final String CATEGORY_BRANCH = "Branch Related Conditions";
	
	public static final String CATEGORY_FILE = "File Related Conditions";
	
	public static final String CATEGORY_APPROVAL = "Approval Related Conditions";
	
	public static final String CATEGORY_BUILD = "Build Verification Conditions";

	public static final String CATEGORY_MISC = "Other Conditions";
	
	/**
	 * Check specified pull request.
	 * 
	 * @param request
	 * 			pull request to be checked. Note that <tt>request.getId()</tt>
	 * 			may return <tt>null</tt> to indicate a push operation, and in 
	 * 			this case, we should not invite any users to vote for the 
	 * 			request 
	 * @return
	 */
	CheckResult check(PullRequest request);

}
