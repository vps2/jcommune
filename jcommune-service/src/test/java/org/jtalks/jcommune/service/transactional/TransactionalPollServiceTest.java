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
package org.jtalks.jcommune.service.transactional;

/**
 * @author Anuar Nurmakanov
 */
public class TransactionalPollServiceTest {
    /*private static final int VOTES_COUNT = 4;
    private static final Long POLL_ID = 1L;
    private PollService pollService;
    @Mock
    private ChildRepository<PollItem> pollOptionDao;
    @Mock
    private ChildRepository<Poll> pollDao;
    @Mock
    private SecurityService securityService;
    @Mock
    private CompoundAclBuilder<User> aclBuilder;
    @Mock
    private TemporaryAuthorityManager temporaryAuthorityManager;


    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        pollService = new TransactionalPollService(pollDao, pollOptionDao,
                securityService, temporaryAuthorityManager);

        Mockito.when(aclBuilder.write()).thenReturn(aclBuilder);
        Mockito.when(aclBuilder.on(Mockito.any(Poll.class))).thenReturn(aclBuilder);
        Mockito.when(securityService.<User>createAclBuilder()).thenReturn(aclBuilder);

    }

    @Test
    public void testAddSingleVote() {
        List<Long> pollOptionIds = Arrays.asList(1L);
        Poll poll = createPollWithOptions(POLL_ID, pollOptionIds, VOTES_COUNT, null);

        Mockito.when(pollDao.get(POLL_ID)).thenReturn(poll);

        Poll resultPoll = pollService.vote(POLL_ID, pollOptionIds);
        PollItem resultPollOption = resultPoll.getPollItems().get(0);

        Assert.assertEquals(resultPollOption.getVotesCount(), VOTES_COUNT + 1,
                "Count of votes should be increased.");
    }

    @Test
    public void testAddSingleVoteInInactivePoll() {
        List<Long> pollOptionIds = Arrays.asList(1L);
        DateTime endingDate = new DateTime(1999, 1, 1, 1, 1, 1, 1);
        Poll poll = createPollWithOptions(POLL_ID, pollOptionIds, VOTES_COUNT, endingDate);

        Mockito.when(pollDao.get(POLL_ID)).thenReturn(poll);

        Poll resultPoll = pollService.vote(POLL_ID, pollOptionIds);
        PollItem resultPollOption = resultPoll.getPollItems().get(0);

        Assert.assertEquals(resultPollOption.getVotesCount(), VOTES_COUNT,
                "Count of votes should be the same.");
    }

    @Test
    public void testAddMultipleVotes() {
        List<Long> pollOptionIds = Arrays.asList(1L, 5L, 9L);
        Poll poll = createPollWithOptions(POLL_ID, pollOptionIds, VOTES_COUNT, null);

        Mockito.when(pollDao.get(Mockito.anyLong())).thenReturn(poll);

        Poll resultPoll = pollService.vote(POLL_ID, pollOptionIds);

        for (PollItem option : resultPoll.getPollItems()) {
            Assert.assertEquals(option.getVotesCount(), VOTES_COUNT + 1,
                    "Count of votes should be increased.");
        }
    }

    @Test
    public void testAddMultipleVotesInInactivePoll() {
        List<Long> pollOptionIds = Arrays.asList(1L, 5L, 9L);
        DateTime endingDate = new DateTime(1999, 1, 1, 1, 1, 1, 1);
        Poll poll = createPollWithOptions(POLL_ID, pollOptionIds, VOTES_COUNT, endingDate);

        Mockito.when(pollDao.get(Mockito.anyLong())).thenReturn(poll);

        Poll resultPoll = pollService.vote(POLL_ID, pollOptionIds);

        for (PollItem option : resultPoll.getPollItems()) {
            Assert.assertEquals(option.getVotesCount(), VOTES_COUNT,
                    "Count of votes should be the same.");
        }
    }

    @Test
    public void testAddIncorrectVotes() {
        List<Long> pollOptionIds = Arrays.asList(1L, 5L, 9L);
        List<Long> incorrectPollOptionIds = Arrays.asList(11L, 13L);
        DateTime endingDate = new DateTime(1999, 1, 1, 1, 1, 1, 1);
        Poll poll = createPollWithOptions(POLL_ID, pollOptionIds, VOTES_COUNT, endingDate);

        Mockito.when(pollDao.get(Mockito.anyLong())).thenReturn(poll);

        Poll resultPoll = pollService.vote(POLL_ID, incorrectPollOptionIds);

        for (PollItem option : resultPoll.getPollItems()) {
            Assert.assertEquals(option.getVotesCount(), VOTES_COUNT,
                    "Count of votes should be the same.");
        }
    }

    private Poll createPollWithOptions(Long pollId, List<Long> pollOptionIds,
                                       int initialVoteCount, DateTime endingDate) {
        Poll poll = new Poll("Poll");
        poll.setEndingDate(endingDate);
        poll.setId(pollId);
        for (Long id : pollOptionIds) {
            PollItem option = new PollItem("Option:" + String.valueOf(id));
            option.setId(id);
            option.setVotesCount(initialVoteCount);
            poll.addPollOptions(option);
        }
        return poll;
    }*/
}
