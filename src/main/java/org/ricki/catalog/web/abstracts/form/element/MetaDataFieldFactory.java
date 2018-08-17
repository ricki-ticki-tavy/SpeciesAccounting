package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.common.Styles;
import org.ricki.catalog.web.abstracts.form.component.referenceField.ReferenceField;
import org.ricki.catalog.web.abstracts.form.component.referenceField.TableReferenceField;
import org.ricki.catalog.web.abstracts.form.element.annotations.FieldType;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.numeric.NumericFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.tableReferencs.TableReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.textArea.TextAreaFieldMetadata;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Map;

public class MetaDataFieldFactory {

  private GridLayout formLayout;
  private Map<String, FormElement> formElementsMap;

  public FormElement createMetaFieldFromMetaDate(Annotation abstractFieldMetadata) {
    FormElement newElement;
    if (abstractFieldMetadata instanceof TextFieldMetadata) {
      newElement = createTextMetaField((TextFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof TextAreaFieldMetadata) {
      newElement = createTextAreaMetaField((TextAreaFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof BooleanFieldMetadata) {
      newElement = createCheckBoxMetaField((BooleanFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof ComboBoxFieldMetadata) {
      newElement = createComboBoxMetaField((ComboBoxFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof ReferenceFieldMetadata) {
      newElement = createReferenceMetaField((ReferenceFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof TableReferenceFieldMetadata) {
      newElement = createCollectionMetaField((TableReferenceFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof NumericFieldMetadata) {
      newElement = createNumericMetaField((NumericFieldMetadata) abstractFieldMetadata);
    } else {
      throw new RuntimeException("Не поддерживаемый тип метеданных " + abstractFieldMetadata.toString());
    }

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------

  private int addCaption(FormElement formElement, String caption, int column, int row, int cellWidth) {
    if (!StringUtils.isEmpty(caption)) {
      HorizontalLayout captionLayout = new HorizontalLayout();
      captionLayout.setSizeFull();

      formElement.caption = new Label(caption);
      formElement.caption.addStyleName(Styles.FORM_FIELD_CAPTION_ENABLEDMODE_STYLE);
      captionLayout.addComponent(formElement.caption);

      formLayout.addComponent(captionLayout, column, row, column + cellWidth - 1, row);
      return column + cellWidth;
    } else {
      return column;
    }

  }
  //--------------------------------------------------------------------------------------------------------------------

  public MetaDataFieldFactory(GridLayout formLayout
          , Map<String, FormElement> formElementsMap) {
    this.formLayout = formLayout;
    this.formElementsMap = formElementsMap;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление ссылочного поля на форму
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createReferenceMetaField(ReferenceFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();
    newElement.fieldType = FieldType.REFERENCE;

    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    ReferenceField refField = new ReferenceField(fieldMetadata.entitySelectorFormClass());
    newElement.field = refField;
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму поля с выпадающим списком
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createComboBoxMetaField(ComboBoxFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();
    newElement.fieldType = FieldType.COMBOBOX;

    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    ComboBox cb = new ComboBox();

    cb.setItems(fieldMetadata.enumList().getEnumConstants());
    newElement.field = cb;
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму логического поля, флажка, переключателя
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createCheckBoxMetaField(BooleanFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();
    newElement.fieldType = FieldType.CHECKBOX;

    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    newElement.field = new CheckBox();
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            columnForField, fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму поля для ввода многострочного текста
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createTextAreaMetaField(TextAreaFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();

    newElement.fieldType = FieldType.MULTI_LINE_TEXT;
    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    newElement.field = new TextArea();
    newElement.field.setSizeFull();
    newElement.field.setHeight(fieldMetadata.height(), Sizeable.Unit.PIXELS);

    String fieldName = fieldMetadata.fieldName();

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());


    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму поля для ввода  текста
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createTextMetaField(TextFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();

    newElement.fieldType = FieldType.TEXT;
    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    newElement.field = new TextField();
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }

  /**
   * Добавление на форму поля для ввода  текста
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createNumericMetaField(NumericFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement(fieldMetadata);

    newElement.fieldType = FieldType.NUMERIC;
    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    newElement.field = new TextField();
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }

  /**
   * Добавление на форму поля c таблицей подчиненных записей
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createCollectionMetaField(TableReferenceFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();

    newElement.fieldType = FieldType.COLLECTION;
    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    TableReferenceField grid = new TableReferenceField(true) {
      @Override
      public void addUserActions(SimpleToolBar simpleToolBar) {
      }
    };
    newElement.field = grid;
    grid.initGrid(fieldMetadata.entityClass(), fieldMetadata);
    String fieldName = fieldMetadata.fieldName();
    grid.setWidth(100, Sizeable.Unit.PERCENTAGE);
    grid.setHeight(fieldMetadata.height(), Sizeable.Unit.PIXELS);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }

}

/**
 * Конфигуратор
 * <p>
 * I. Описание структуры данных
 * <p>
 * Описание сущностей системы. Их полей, ограничений, зависимостей от данных в полях, связей с другими сущностями
 * <p>
 * 1) верификации:
 * а) примитивные, на уровне галочек;
 * б) простые зависимости от заполненности других полей ( с учетом возможной необходимости сброса
 * значения поля при изменении значения поля, от которого есть зависимость
 * в) зависимости, реализуемые написанием кода. Подробно рассматривается в части
 * "Программирование пользовательской логики"
 * 2) правила кеширования сущностей и транслировать их в настройки текущего менеджера кеша L2
 * <p>
 * <p>
 * <p>
 * <p>
 * II. Метаданные форм
 * <p>
 * Метаданные формы содержат описание внешнего вида формы, мапинг визуальных полей на данные, пользовательскую
 * логику, реализация которой рассматривается в части "Программирование пользовательской логики".
 * <p>
 * 1) Формат хранения метаданных должен позволять ручное вмешательство, то есть иметь структуру, доступную для
 * чтения/редактирования любыми текстовыми редакторами.
 * а) Тут предлагается использовать готовый формат от vaadin так как преобразование такого файла в форму
 * на экране уже реализовано и не требует затраты сил. Возможно потребуется расширение формата или
 * дополнения для подклчения логики пользователя и специфических преобразований данных между моделью
 * и представлением.
 * <p>
 * 2) Далее потребуется визуальный редактор, который в начале можно не создавать
 * <p>
 * 3) Подключение безопасности. Например в виде вызовов к бину, реализуемому и опционально подключаемому в
 * проект модулем "БЕЗОПАСНОСТЬ"
 * <p>
 * <p>
 * III. Внешние модули
 * <p>
 * <p>
 * 1) Допустить возможность разработки и подключения форм, написанных и скомпилированных в виде отдельных JAR
 * файлов.
 * а) принять хранилищем таких файлов основную БД, что даст возможность выполнение бэкапа и восстановления
 * из него состояние всего приложения в целом
 * б) допустить в одном файле хранить более одной формы. Это необходимо для реализации сложного многооконного
 * функционала и целых модулей
 * в) выбрать в качестве языка Java. Преимущества данного подхода в возможности легкой отладки таких модулей
 * д) файл должен неким образом поддерживать безопасность, содержать метаданные сущностей, создаваемых в
 * модуле и, возможно метаданные для постороения форм
 * е) допускается полностью ручное программирование форм.
 * ж) Допускать динамическую загрузку/обновление/выгрузку таких модулей
 * <p>
 * 2) Для компиляции такой библиотеки может потребоваться передача разработчиками интерфейсов, описывающих
 * методы основного приложения. Отладку можно осуществлять в составе развернутого приложения.
 * 3) решить опять же вопрос динамического переопределения метаданных ORM
 * <p>
 * <p>
 * IV. Хранение данных
 * <p>
 * Данные предлагается хранить в базе с использованием ОРМ. Подключение кеширования - опциональное для
 * возможности подключения к таким системам как Ignite и прочее
 * <p>
 * 1) Необходимо реализовать динамичечки формируемые классы (или иные варианты) сущностей.
 * 2) Желательно реализовать частичную/полную перезагрузку метаданных без перезагрузки приложения.
 * 3)
 * <p>
 * <p>
 * <p>
 * V. Безопасность
 * <p>
 * Опциональный блок, базирующийся на справочнике ролей/прав/пользователей и подключаемый в основной проект
 * как бин, реализующий специальный интерфейс.
 * <p>
 * 1) Виды доступа:
 * а) Удаление/редактирование
 * б) создание
 * в) чтение
 * <p>
 * 2) Доступ к сущностям в зависимости от:
 * а) их владельца / группы(отдела)
 * б) значения данных сущности или данных вложенных сущностей
 * в)
 * <p>
 * 3) Доступ к визуальным формам или функционалу
 * а) в зависимости от прав пользователя/группы
 * б) значения данных сущности или данных вложенных сущностей ()
 */
