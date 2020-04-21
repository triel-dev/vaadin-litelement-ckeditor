package com.wontlost.ckeditor.sample;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.wontlost.ckeditor.EditorType;
import com.wontlost.ckeditor.VaadinCKEditor;
import com.wontlost.ckeditor.VaadinCKEditorBuilder;

@Route("")
@PWA(name = "CKEditor", shortName = "CK")
public class MainView extends VerticalLayout {

	public MainView() {
		super();
		VaadinCKEditor editor = new VaadinCKEditorBuilder().with(builder -> builder.editorType = EditorType.CLASSIC).createVaadinCKEditor();
		add(new Label(""));
		Comment comment = new Comment();
		comment.setMessage("Message");
		Binder<Comment> binder = new Binder<>(Comment.class);
		editor.setReadOnly(true);
		binder.forField(editor).bind(Comment::getMessage, Comment::setMessage);
		binder.readBean(comment);
		editor.addValueChangeListener(event -> System.out.println(editor.getValue()));
		add(editor);

		Button saveButton = new Button("Save",
				event -> {
					try {
						binder.writeBean(comment);
						System.out.println("--------->"+comment.getMessage());
					} catch (ValidationException e) {
						System.out.println(e.getMessage());
					}
				});
		add(new Label(""));
		add(saveButton);
		Button button = new Button("Print");
		Label label = new Label(editor.getValue());
		button.addClickListener(e->label.setText(editor.getValue()));
		add(button);
		add(new Label(""));
		add(label);
		add(new Label(""));
//		Div div = new Div();
//		div.getElement().setProperty("innerHTML", "<form action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\" target=\"_top\" style=\"display:flex; align-items:center; height: 100%; \"> "+
//				"<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\" /> "+
//				"<input type=\"hidden\" name=\"hosted_button_id\" value=\"7GG7XSYJ4TZFQ\" /> "+
//				"<input type=\"image\" src=\"https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif\" border=\"0\" name=\"submit\" title=\"PayPal - The safer, easier way to pay online!\" alt=\"Donate with PayPal button\" />"+
//				"<img alt=\"\" border=\"0\" src=\"https://www.paypal.com/en_NZ/i/scr/pixel.gif\" width=\"1\" height=\"1\" />"+
//				"</form> ");
//		add(div);
//		VaadinCKEditor editor1 = new VaadinCKEditorBuilder().with(builder->{
//			builder.editorType=EditorType.BALLOON;
//			builder.editorData="Balloon Editor test";
//		}).createVaadinCKEditor();
//		add(editor1);
//
//		add(new Label(""));
//
//		VaadinCKEditor editor2 = new VaadinCKEditorBuilder().with(builder->{
//			builder.editorType=EditorType.INLINE;
//			builder.editorData="Inline";
//		}).createVaadinCKEditor();
//		add(editor2);
//
//		add(new Label(""));
//
//		VaadinCKEditor editor3 = new VaadinCKEditorBuilder().with(builder->{
//			builder.editorType=EditorType.DECOUPLED;
//			builder.editorData="Dcoupled Editor";
//		}).createVaadinCKEditor();
//		add(editor3);



		setAlignItems(Alignment.CENTER);
	}

}