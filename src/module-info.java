module AlaGym {
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.web;
	requires javafx.base;
	requires java.sql;

	opens application;
	opens model.entities;
	opens model.dao;
	opens view;
	opens view.utils;
	opens controller;
	opens db;
}
