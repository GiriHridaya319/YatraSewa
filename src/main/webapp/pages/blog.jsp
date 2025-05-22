<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/blog.css">
</head>
<body>
<style>
/* Reset and base styles */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
  background-color: #f8f9fa;
  color: #333;
  line-height: 1.6;
}

/* Container styles */
.blog-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

/* Header styles */
.blog-header {
  background: linear-gradient(135deg, #1e5799 0%, #207cca 100%);
  color: white;
  padding: 30px 0;
  text-align: center;
  margin-bottom: 30px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.blog-header h1 {
  font-size: 2.5rem;
  letter-spacing: 1px;
}

/* Blog content layout */
.blog-content {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 40px;
  justify-content: space-between;
}

/* Blog post styles */
.blog-post {
  display: flex;
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
  width: 100%;
  margin-bottom: 30px;
  transition: transform 0.3s ease;
}

.blog-post:hover {
  transform: translateY(-5px);
}

.blog-image {
  flex: 0 0 35%;
  position: relative;
  overflow: hidden;
}

.blog-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.5s ease;
}

.blog-post:hover .blog-image img {
  transform: scale(1.05);
}

.blog-details {
  flex: 0 0 65%;
  padding: 25px;
}

.blog-date {
  color: #666;
  font-size: 0.9rem;
  margin-bottom: 10px;
}

.blog-details h2 {
  color: #1e5799;
  margin-bottom: 15px;
  font-size: 1.8rem;
}

.blog-description {
  margin-bottom: 20px;
  color: #555;
  line-height: 1.8;
}

/* Blog meta info */
.blog-meta {
  display: flex;
  justify-content: space-between;
  padding-top: 15px;
  border-top: 1px solid #eee;
  color: #777;
  font-size: 0.9rem;
}

.blog-author {
  font-weight: 600;
}

.blog-comments {
  color: #1e5799;
}

/* Categories sidebar */
.blog-categories {
  flex: 0 0 25%;
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
  align-self: flex-start;
}

.blog-categories h3 {
  color: #1e5799;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 2px solid #eee;
}

.blog-categories ul {
  list-style: none;
}

.blog-categories li {
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f0;
  display: flex;
  justify-content: space-between;
  transition: padding 0.3s ease;
}

.blog-categories li:hover {
  padding-left: 5px;
  color: #1e5799;
}

.blog-categories span {
  color: #999;
  font-size: 0.9rem;
}

/* Responsive adjustments */
@media (max-width: 992px) {
  .blog-post {
    flex-direction: column;
  }
  
  .blog-image, .blog-details {
    flex: 0 0 100%;
  }
  
  .blog-image {
    height: 250px;
  }
}

@media (max-width: 768px) {
  .blog-content {
    flex-direction: column;
  }
  
  .blog-categories {
    flex: 0 0 100%;
    margin-top: 30px;
    margin-bottom: 30px;
  }
  
  .blog-header h1 {
    font-size: 2rem;
  }
}
</style>
<div class="blog-container">
    <!-- Blog Header -->
    <header class="blog-header">
        <h1>YatraSewa Blogs</h1>
    </header>

    <!-- Blog Content -->
    <div class="blog-content">
        <!-- Blog Post Section 1 -->
        <div class="blog-post">
            <div class="blog-image">
                <img src="blog1.jpg" alt="Kathmandu to Pokhara trip image">
            </div>
            <div class="blog-details">
                <p class="blog-date">14.11.2024</p>
                <h2>Kathmandu to Pokhara trip travel guide</h2>
                <p class="blog-description">
                    Traveling from Kathmandu to Pokhara is one of the most scenic and popular journeys in Nepal.
                    <br>The route is a blend of cultural experiences, breathtaking landscapes, and stunning mountain views.
                </p>
                <div class="blog-meta">
                    <span class="blog-author">Bus Sewa</span>
                    <span class="blog-comments">3 comments</span>
                </div>
            </div>
        </div>

        <!-- Blog Categories Section -->
        <aside class="blog-categories">
            <h3>Blog Categories</h3>
            <ul>
                <li>News <span>(1)</span></li>
                <li>Travel Blog <span>(2)</span></li>
            </ul>
        </aside>
    </div>

    <!-- Blog Post Section 2 -->
    <div class="blog-content">
        <div class="blog-post">
            <div class="blog-image">
                <img src="blog2.jpg" alt="Pokhara to Chitwan trip image">
            </div>
            <div class="blog-details">
                <p class="blog-date">16.11.2024</p>
                <h2>Pokhara to Chitwan trip travel guide</h2>
                <p class="blog-description">
                    Traveling from the serene city of Pokhara to the wilderness of Chitwan is an exciting journey
                    that combines natural beauty, cultural richness, and thrilling adventures. This trip offers a 
                    unique opportunity to experience Nepal's contrasting landscapes from the tranquil lakes of 
                    Pokhara to the dense jungles of Chitwan.
                </p>
                <div class="blog-meta">
                    <span class="blog-author">Bus Sewa</span>
                    <span class="blog-comments">3 comments</span>
                </div>
            </div>
        </div>
    </div>

    <!-- Blog Post Section 3 -->
    <div class="blog-content">
        <div class="blog-post">
            <div class="blog-image">
                <img src="blog3.jpg" alt="Highway Update image">
            </div>
            <div class="blog-details">
                <p class="blog-date">19.11.2024</p>
                <h2>Highway Update</h2>
                <p class="blog-description">
                    
                </p>
                <div class="blog-meta">
                    <span class="blog-author">Bus Sewa</span>
                    <span class="blog-comments">3 comments</span>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>