package com.example.androidlessons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlessons.databinding.FragmentMailBinding

class MailFragment : Fragment() {
    private var _binding: FragmentMailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mailAdapter: MailAdapter
    private lateinit var mailList: List<Mail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMailBinding.inflate(inflater, container, false)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        mailList = generateDummyMails()
        mailAdapter = MailAdapter(mailList)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = mailAdapter

        return binding.root
    }

    private fun generateDummyMails(): List<Mail> {
        return listOf(
            Mail("Напоминание о встрече", "Не забудьте о встрече завтра в 10:00. Отправитель: ivan.petrov@example.com Дата: 2023-10-01"),
            Mail("Еженедельная рассылка", "Посмотрите наши последние обновления и предложения! Отправитель: newsletter@example.com Дата: 2023-10-02"),
            Mail("Обновление проекта", "Проект идет по графику и будет завершен в конце месяца. Отправитель: manager@example.com Дата: 2023-10-03"),
            Mail("Счет #12345", "Ваш счет за прошлый месяц прикреплен. Отправитель: billing@example.com Дата: 2023-10-04"),
            Mail("Приглашение на день рождения", "Вы приглашены на мой день рождения в субботу! Отправитель: friend@example.com Дата: 2023-10-05"),
            Mail("Заявка на работу", "Спасибо за вашу заявку на позицию разработчика ПО. Отправитель: hr@example.com Дата: 2023-10-06"),
            Mail("Путешествие", "Ваш маршрут для предстоящей поездки прикреплен. Отправитель: travel@example.com Дата: 2023-10-07"),
            Mail("Запрос на обратную связь", "Мы будем рады услышать ваше мнение о нашем сервисе. Отправитель: support@example.com Дата: 2023-10-08"),
            Mail("Предупреждение о безопасности", "Обнаружена необычная попытка входа в вашу учетную запись. Отправитель: security@example.com Дата: 2023-10-09"),
            Mail("Рецепт дня", "Попробуйте этот вкусный рецепт для быстрого ужина! Отправитель: chef@example.com Дата: 2023-10-10")
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().finishAffinity()
        return true
    }
}