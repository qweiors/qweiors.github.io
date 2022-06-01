package com.example.librarybackend.service;
import java.util.ArrayList;
import java.util.List;

public class BorrowService {

    BorrowOption bo = new BorrowOption();
    UserOption uo = new UserOption();
    nowDate nd = new nowDate();
    ReserveOption ro = new ReserveOption();
    LogOption lo = new LogOption();

    BookOption booko = new BookOption();
    //判断是否有罚款

    public String getRegularTime(int year, int month, int day)
    {
        if(month < 10 && day < 10)
            return year + "/" + "0" + month + "/" + "0" + day;
        if(month < 10 && day >= 10)
            return year + "/" + "0" + month + "/" + day;
        if(month >= 10 && day < 10)
            return year + "/" + month + "/" + "0" + day;
        return year + "/" + month + "/" + day;
    }

    public String getRegularTime(int year, int month, int day, int hour)
    {
        if(hour < 10) return getRegularTime(year, month, day) + "/0" + hour;
        return getRegularTime(year, month, day) + "/" + hour;
    }

    public Boolean haveFined(String userId) {
        List<borrowEvent> borrowEvents = bo.selectByUserId(userId);
        for(borrowEvent event : borrowEvents)
        {
            String time = getRegularTime(event.getYear(), event.getMonth(), event.getDay());
            String needReturn = nd.addDay(time, event.getBorrowDay());
            if(nd.cmp(nd.getDate(), needReturn)) return true;
        }

        User user = uo.selectById(userId);
        if(user.getAccount() < 0) return true;

        return false;
    }

    //查询一共多少人未支付罚款  Total Fine(UnPaid)
    public List<User> haveAllFined()
    {
        UserOption uo = new UserOption();
        List<User> users = new ArrayList<>();
        users = uo.selectAllUser();
        List<User> usersFined = new ArrayList<>();
        for(User user : users)
        {
            List<borrowEvent> borrowEvents = bo.selectByUserId(user.getId());
            for(borrowEvent event : borrowEvents)
            {
                String time = getRegularTime(event.getYear(), event.getMonth(), event.getDay());
                String needReturn = nd.addDay(time, event.getBorrowDay());
                if(nd.cmp(nd.getDate(), needReturn)) usersFined.add(user);
            }
            if(user.getAccount() < 0) usersFined.add(user);
        }
        return usersFined;
    }

    //判断某本书是否被预定
    public Boolean haveReserve(String bookId, String userId) {
        ReserveOption reserveOption = new ReserveOption();
        reserveEvent event = ro.selectByBookId(bookId);

        if(event == null) return false;

        String time = getRegularTime(event.getYear(), event.getMonth(), event.getDay(), event.getHour());
        System.out.println(nd.getDate() + " " + nd.addHour(time, event.getReserveHour()));
        if(nd.cmp(nd.getDate(), nd.addHour(time, event.getReserveHour())))
        {
            ro.removeReserve(bookId);
            return false;
        }

        if(event.getUserId().equals(userId)) return false;

        return true;
    }

    //借书服务(借书时访问该书是否被借走(与数据库交互层中存在通过书的Id查询书籍的API)，注意填日志，还书日期填写“unreturned”，函数刚开始时要知道该用户是否有罚款，有罚款不允许借书)
    public Boolean borrowBook(String userId, String bookId) {
        if(haveFined(userId)) return false;
        if(bo.selectByUserId(userId).size() >= 5) return false;
        if(haveReserve(bookId, userId)) return false;
        if(bo.selectByBookId(bookId) != null) return false;

        String nowDate = nd.getDate();
        int nowYear = nd.getYear(nowDate);
        int nowMonth = nd.getMonth(nowDate);
        int nowDay = nd.getDay(nowDate);
        borrowEvent event = new borrowEvent(userId, bookId, nowYear, nowMonth, nowDay, 10);
        Book book = booko.selectById(bookId);
        book.setBorrowed('1');
        booko.updateById(bookId, book);
        Log log = new Log(userId, bookId, nowDate.substring(0, nowDate.length()-3), "borrow no returned");
        lo.insertIntoLog(log);
        return bo.insertIntoBorrow(event);
    }

    public String borrowBookByISBN(String ISBN, String userId)
    {
        if(haveFined(userId)) return "";
        if(bo.selectByUserId(userId).size() >= 5) return "";

        List<Book> books = booko.selectByISBN(ISBN);
        for(Book book : books)
        {
            if(borrowBook(userId, book.getId())) return book.getId();
        }
        return "";
    }

    public Boolean returnBook(String userId, String bookId) {
        String nowDate = nd.getDate();

        int days = 0;

        borrowEvent event = bo.selectByBookId(bookId);
        if(event == null) return false;

        String borrowDate = getRegularTime(event.getYear(), event.getMonth(), event.getDay());
        String needReturn = nd.addDay(borrowDate, event.getBorrowDay());

        StringBuffer time = new StringBuffer(needReturn);
        while(nd.cmp(nowDate, time.toString()))
        {
            days++;
            time = new StringBuffer(nd.addOne(time.toString()));
        }

        User user = uo.selectById(userId);
        user.setAccount(user.getAccount() - days);
        uo.update(userId, user);

        Book book = booko.selectById(bookId);
        book.setBorrowed('0');
        booko.updateById(bookId, book);

        Log log = new Log(userId, bookId, borrowDate, nowDate.substring(0, nowDate.length()-3));
        lo.insertIntoLog(log);

        return bo.removeBorrow(bookId);
    }

    public Boolean renewBook(String bookId) {
        borrowEvent event = bo.selectByBookId(bookId);
        return bo.updateBorrow(bookId, event.getBorrowDay() + 10);
    }

    public List<borrowEvent> findByUserId(String userId) {
        return bo.selectByUserId(userId);
    }

    public int getNotBorrowNum(String ISBN)
    {
        List<Book> books = booko.selectByISBN(ISBN);
        int num = 0;
        for(Book book : books)
        {
            if(book.getBorrowed() == '0') num++;
        }
        return num;
    }

}
