package org.starter.kotlin.config


import javax.sql.DataSource

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate

import org.starter.kotlin.model.Book
import org.starter.kotlin.batch.BookItemProcessor

@Configuration
@EnableBatchProcessing
@DependsOn("dataSource")
open class BatchConfiguration {

	@Autowired
	lateinit var jobBuilderFactory: JobBuilderFactory

	@Autowired
	lateinit var stepBuilderFactory: StepBuilderFactory

	@Autowired
	lateinit var bookItemProcess: BookItemProcessor
	
	@Autowired
	lateinit var dataSource: DataSource

	@Bean
	open fun reader(): FlatFileItemReader<Book> {
		return FlatFileItemReaderBuilder<Book>()
				.name("bookItemReader")
				.resource(ClassPathResource("data/sample-data.csv"))
				.delimited()
				.names("title", "content", "author")
				.fieldSetMapper(object:BeanWrapperFieldSetMapper<Book>() {
					init{
						setTargetType(Book::class.java)
					}
				})
				.build()
	}

	@Bean
	open fun processor(): BookItemProcessor {
		return BookItemProcessor()
	}

	
	// Refer to this url http://atmarkplant.com/spring-batch-reader-processor-writer/
	@Bean
	open fun writer(dataSource: DataSource): JdbcBatchItemWriter<Book> {
		return JdbcBatchItemWriterBuilder<Book>()
				.itemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider())
				.sql("INSERT INTO book (title, content, author) VALUES (:title, :content, :author)")
				.dataSource(dataSource)
				.build()
	}

	@Bean
	open fun importBookJob(): Job {
		return jobBuilderFactory
				.get("importBookJob")
				.incrementer(RunIdIncrementer())
                .flow(step1())
                .end()
                .build()
	}
	
	@Bean
	open fun step1(): Step {
		return stepBuilderFactory
				.get("step1")
				.chunk<Book, Book>(10)
                .reader(reader())
                .processor(processor())
                .writer(writer(dataSource))
                .build()
	}
}