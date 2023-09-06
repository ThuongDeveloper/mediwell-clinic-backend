/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.News;
import groub2.backend.res.NewsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class NewsService {
    @Autowired
    NewsRepository res;

    public List<News> findAll() {
        return res.findAll();
    }

    public News getNewsById(Integer id) {
        return res.findById(id).orElse(null);
    }

    public void addNews(News newNews) {
        newNews.setCreateAt(new java.util.Date());
        res.save(newNews);
    }

    public News updateNews(News updatedNews) {
        News news = res.findById(updatedNews.getId()).orElse(null);
        if (news != null) {
            // Kiểm tra xem các trường có được cung cấp để cập nhật hay không
            if (updatedNews.getTitle()!= null) {
                news.setTitle(updatedNews.getTitle());
            }
            if (updatedNews.getContent()!= null) {
                news.setContent(updatedNews.getContent());
            }
            if (updatedNews.getStatus()!= null) {
                news.setStatus(updatedNews.getStatus());
            }

            // Cập nhật ngày tạo mới
            news.setCreateAt(new java.util.Date());

            // Lưu đối tượng Casher đã cập nhật vào cơ sở dữ liệu
            return res.save(news);
        }
        return null;
    }

    public void deleteNews(Integer id) {
        res.deleteById(id);
    }
}
