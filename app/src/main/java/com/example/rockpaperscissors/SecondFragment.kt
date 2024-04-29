package com.example.rockpaperscissors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rockpaperscissors.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val PAPER_CHOICE = "Paper"
    private val SCISSORS_CHOICE = "Scissors"
    private val ROCK_CHOICE = "Rock"
    private var humanScore = 0
    private var computerScore = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.bRock.setOnClickListener {
            binding.ivHumanChoice.setImageResource(R.drawable.rock)
            Toast.makeText(view.context, play(ROCK_CHOICE), Toast.LENGTH_SHORT).show()
            binding.tvScore.text = "Score: Human $humanScore Computer $computerScore"
        }
        binding.bPaper.setOnClickListener {
            binding.ivHumanChoice.setImageResource(R.drawable.paper)
            Toast.makeText(view.context, play(PAPER_CHOICE), Toast.LENGTH_SHORT).show()
            binding.tvScore.text = "Score: Human $humanScore Computer $computerScore"
        }
        binding.bScissors.setOnClickListener {
            binding.ivHumanChoice.setImageResource(R.drawable.scissors)
            Toast.makeText(view.context, play(SCISSORS_CHOICE), Toast.LENGTH_SHORT).show()
            binding.tvScore.text = "Score: Human $humanScore Computer $computerScore"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun play(playerChoice: String) : String {
        val choiceArr = arrayOf(PAPER_CHOICE, SCISSORS_CHOICE, ROCK_CHOICE)
        var randomValue = (0..2).random();
        var computerChoice = choiceArr[randomValue]
        when (computerChoice) {
            PAPER_CHOICE -> {
                binding.ivComputerChoice.setImageResource(R.drawable.paper)
            }
            SCISSORS_CHOICE -> {
                binding.ivComputerChoice.setImageResource(R.drawable.scissors)
            }
            ROCK_CHOICE -> {
                binding.ivComputerChoice.setImageResource(R.drawable.rock)
            }
        }
        if (playerChoice == ROCK_CHOICE && computerChoice == SCISSORS_CHOICE) {
            humanScore++
            return "Rock crushes scissors. You win!"
        } else if (playerChoice == ROCK_CHOICE && computerChoice == PAPER_CHOICE) {
            computerScore++
            return "Paper covers rock. Computer wins!"
        } else if (playerChoice == SCISSORS_CHOICE && computerChoice == ROCK_CHOICE) {
            computerScore++
            return "Rock crushes scissors. Computer wins!"
        } else if (playerChoice == SCISSORS_CHOICE && computerChoice == PAPER_CHOICE) {
            humanScore++
            return "Scissors cuts paper. You win!"
        } else if (playerChoice == PAPER_CHOICE && computerChoice == ROCK_CHOICE) {
            humanScore++
            return "Rock crushes scissors. You win!";
        } else if (playerChoice == PAPER_CHOICE && computerChoice == SCISSORS_CHOICE) {
            computerScore++
            return "Scissors cuts paper. Computer wins!"
        } else {
            return "Draw. Nobody won."
        }
    }
}