/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.jcommune.web.validation.validators;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.jtalks.jcommune.model.entity.PollItem;
import org.jtalks.jcommune.web.dto.PollDto;
import org.jtalks.jcommune.web.dto.TopicDto;
import org.jtalks.jcommune.web.validation.annotations.ValidPoll;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator for object with {@link ValidPoll} annotation. Validation rules description
 * please see on the next link {@link http://jira.jtalks.org/secure/attachment/12047/AC_Polling.txt}
 *
 * @author Alexandre Teterin
 *         Date: 14.04.12
 */


public class PollValidator implements ConstraintValidator<ValidPoll, Object> {
    private int minItemsNumber;
    private int maxItemsNumber;
    private int minItemsLength;
    private int maxItemsLength;
    private String pollTitleName;
    private String pollTitleValue;
    private String pollItemsName;
    private String pollItemsValue;
    private String endingDateName;
    private String endingDateValue;
    private String message;
    private List<PollItem> items;

    private final String ITEMS_NUMBER_MESSAGE = "{VotingOptionsNumber.message}";
    private final String FUTURE_DATE_MESSAGE = "{javax.validation.constraints.Future.message}";
    private final String TITLE_NOT_BLANK_IF_ITEMS_NOT_BLANK_MESSAGE = "{PollTitleNotBlankIfPollItemsNotBlank.message}";
    private final String ITEMS_NOT_BLANK_IF_TITLE_NOT_BLANK_MESSAGE = "{PollItemsNotBlankIfPollTitleNotBlank.message}";
    private final String ITEM_LENGTH_MESSAGE = "{VotingItemLength.message}";

    /**
     * Initialize validators fields from annotated class instance.
     *
     * @param constraintAnnotation {@link ValidPoll} class annotation.
     */
    @Override
    public void initialize(ValidPoll constraintAnnotation) {
        this.minItemsNumber = constraintAnnotation.minItemsNumber();
        this.maxItemsNumber = constraintAnnotation.maxItemsNumber();
        this.minItemsLength = constraintAnnotation.minItemsLength();
        this.maxItemsLength = constraintAnnotation.maxItemsLength();
        this.pollTitleName = constraintAnnotation.pollTitle();
        this.pollItemsName = constraintAnnotation.pollItems();
        this.endingDateName = constraintAnnotation.endingDate();
        this.message = constraintAnnotation.message();
    }

    /**
     * Validate object with {@link ValidPoll} annotation.
     *
     * @param value object with object with {@link ValidPoll} annotation.
     * @param context validation context.
     * @return {@code true} if validation successful, otherwise return false.
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        getValidatedFields(value);

        return isVotingOptionsNumberValid(context)
                & isDateInStringFormatInFuture(context)
                & isTitleOrItemsNotBlankIfOneOfThemNotBlank(context)
                & isItemLengthValid(context);
    }

    /**
     * Validate poll item length.
     *
     * @param context validation context.
     * @return {@code true} if validation successful, otherwise return false.
     */
    private boolean isItemLengthValid(ConstraintValidatorContext context) {
        boolean result = true;

        //empty poll items field is valid
        if (!StringUtils.isNotBlank(pollItemsValue)) {
            return result;
        }

        for (PollItem item : items) {
            if ((item.getName().length() < minItemsLength) || (item.getName().length() > maxItemsLength)) {
                result = false;
                constraintViolated(context, ITEM_LENGTH_MESSAGE, pollItemsName);
            }
        }


        return result;
    }

    /**
     * Validate 'poll title or poll items not blank if one of them not blank' condition.
     *
     * @param context validation context.
     * @return {@code true} if validation successful, otherwise return false.
     */
    private boolean isTitleOrItemsNotBlankIfOneOfThemNotBlank(ConstraintValidatorContext context) {
        boolean result = true;
        //title is not blank & items are blank
        if (StringUtils.isNotBlank(pollTitleValue) && !StringUtils.isNotBlank(pollItemsValue)) {
            result = false;
            constraintViolated(context, ITEMS_NOT_BLANK_IF_TITLE_NOT_BLANK_MESSAGE,
                    pollItemsName);
        }

        //title is blank & items are not blank
        if (!StringUtils.isNotBlank(pollTitleValue) && StringUtils.isNotBlank(pollItemsValue)) {
            result = false;
            constraintViolated(context, TITLE_NOT_BLANK_IF_ITEMS_NOT_BLANK_MESSAGE,
                    pollTitleName);
        }

        return result;

    }

    /**
     * Validate the following condition.
     *
     *Item should be more than one (should not be possible to create Poll with just one item,
     * error message appears on trying to save it).
     *
     * @param context validation context.
     * @return {@code true} if validation successful, otherwise return false.
     */
    private boolean isVotingOptionsNumberValid(ConstraintValidatorContext context) {
        boolean result = false;

        if (!StringUtils.isNotBlank(pollTitleValue)) {
            //Poll title is empty so poll will not be created and it not need to check poll items number
            result = true;
        } else {
            if (StringUtils.isNotBlank(pollItemsValue)) {
                if ((items.size() >= minItemsNumber) || (items.size() <= maxItemsNumber)) {
                    result = true;
                }
            }
        }

        if (!result) {
            constraintViolated(context, ITEMS_NUMBER_MESSAGE, pollItemsName);
        }

        return result;
    }

    /**
     * Validate the 'poll ending date in future' condition.
     *
     *
     * @param context validation context.
     * @return {@code true} if validation successful, otherwise return false.
     */
    private boolean isDateInStringFormatInFuture(ConstraintValidatorContext context) {
        boolean result;


        if (endingDateValue == null) {//null values are valid
            result = true;
        } else {
            DateTime date = DateTimeFormat.forPattern(PollDto.DATE_FORMAT).parseDateTime(endingDateValue);
            result = date.isAfter(new DateTime());
            System.out.println();
        }

        if (!result) {
            constraintViolated(context, FUTURE_DATE_MESSAGE, endingDateName);
        }

        return result;
    }

    /**
     * Initialize validated fields values.
     *
     * @param value object to validated.
     */
    private void getValidatedFields(Object value) {
        try {
            pollTitleValue = BeanUtils.getProperty(value, pollTitleName);
            pollItemsValue = BeanUtils.getProperty(value, pollItemsName);
            endingDateValue = BeanUtils.getProperty(value, endingDateName);
            if (StringUtils.isNotBlank(pollItemsValue)) {
                items = TopicDto.parseItems(pollItemsValue);
            } else {
                items = new ArrayList<PollItem>(0);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }


    /**
     * Place violated constraint to context.
     *
     * @param context context to place violated constraint.
     * @param message violated constraint detail message.
     * @param fieldName violated constraint validated object field.
     */
    private void constraintViolated(ConstraintValidatorContext context, String message, String fieldName) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addNode(fieldName)
                .addConstraintViolation();
    }

}
