package com.pmease.gitop.web.page.project.settings.gatekeeper;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.pmease.commons.editable.EditContext;
import com.pmease.commons.editable.EditableUtils;
import com.pmease.gitop.model.gatekeeper.GateKeeper;

@SuppressWarnings("serial")
public abstract class GateKeeperEditor extends Panel {

	private GateKeeper gateKeeper;
	
	public GateKeeperEditor(String id, GateKeeper gateKeeper) {
		super(id);
		this.gateKeeper = gateKeeper;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new WebMarkupContainer("icon").add(AttributeAppender.append("class", EditableUtils.getIcon(gateKeeper.getClass()))));
		add(new Label("name", EditableUtils.getName(gateKeeper.getClass())));
		add(new Label("description", EditableUtils.getDescription(gateKeeper.getClass())).setEscapeModelStrings(false));
		
		Form<Void> form = new Form<Void>("form");
		add(form);
		final EditContext editContext = EditableUtils.getContext(gateKeeper);
		form.add((Component)editContext.renderForEdit("beanEditor"));
		
		form.add(new AjaxLink<Void>("cancel") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				onCancel(target);
			}
			
		});
		
		form.add(new AjaxSubmitLink("save") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				
				editContext.validate();
				if (!editContext.hasValidationError())
					onSave(target);
				else
					target.add(GateKeeperEditor.this);
			}
			
		});
	}

	protected abstract void onCancel(AjaxRequestTarget target);
	
	protected abstract void onSave(AjaxRequestTarget target);
}
